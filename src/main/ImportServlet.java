package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import persistence.PersistenceUtil;

import com.google.common.collect.Lists;

import configs.ErrorEventConfig;
import configs.EventCauseConfig;
import configs.FailureClassConfig;
import configs.MCC_MNCConfig;
import configs.UETypeConfig;
import entity.DatasetEntity;

/**
 * @author Group2<br>
 * 
 * Servlet called by webapp.<br>
 * Uploads file to local storage.<br>
 * Parses the file into the database.<br> 
 */
@SuppressWarnings({"serial", "unchecked"})
public class ImportServlet extends HttpServlet {

	private static DecimalFormat dFormatter = new DecimalFormat("#,###,###");
	private static Workbook excelData;
	private String filePath;
	private int maxFileSize = 100*200*1024;
	private int maxMemSize = 4*1024;
	
	/**
	 * Initializes context-parameter to get file-path.
	 */
	public void init(){
		filePath = getServletContext().getInitParameter("ImportFile");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime = System.nanoTime();
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(!isMultipart){
			out.println("<HTML><HEAD><TITLE>Servlet Upload</TITLE></HEAD>"
					 +  "<BODY><CENTER>No file uploaded!</CENTER></BODY></HTML>");
			return;
		}
		
		createTempFolders();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File("C:\\tmp"));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		File file = null;
		
		try{
			List<FileItem> fileItems = Lists.newArrayList(upload.parseRequest(request).iterator());
			
			out.println("<HTML><HEAD><TITLE>Excel Upload</TITLE></HEAD><BODY><CENTER>");
			
			for(FileItem fi : fileItems){
				if(!fi.isFormField()){
					String fileName = fi.getName();
					
					if(fileName.lastIndexOf("\\") >= 0)
						file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")));
					else
						file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")+1));
					
					fi.write(file);
					out.println("Uploaded File: <B>" + fileName + "</B><BR />");
				}
			}
			loadExcelFile(file.getAbsolutePath(), out);

			PersistenceUtil.useTestDatabase();
			generateDatabase();
			long timeTakenInMillis = (System.nanoTime()-startTime)/1000000;

			out.println("<p>Successfully imported the database.</p>"
				+ "<p>" + dFormatter.format(ErrorEventConfig.errorEvents.size())+ " ErrorEvents added to database."
					+ " (Total: "+ dFormatter.format(PersistenceUtil.numberOfErrorEvents()) + ")"
				+ "<br />"
				+ dFormatter.format(ErrorEventConfig.invalidErrorEvents.size()) + " ErrorEvents removed due to inconsistencies."
					+ " (Total: " + dFormatter.format(PersistenceUtil.numberOfInvalidErrorEvents()) + ")</p>"
				+ "<p>Click <a href=\"webpages/admin/sysHome.jsp\">here</a> to return to your homepage.</p>"
				+ "</CENTER></BODY>"
				+ "<DIV style=\"position: relative\""
				+ "<p style=\"position: fixed; bottom: 0; width=100%; text-align: center\"></p>"
				+ "<p><CENTER>Dataset imported in " + String.format("%.2f", (double) timeTakenInMillis/1000) +" seconds</CENTER></p>"
				+ "</DIV></CENTER></BODY></HTML>");
			out.close();
			
		} catch(FileUploadException e){
			out.println("<script>alert(\"File too large!\");window.location.replace(\"webpages/admin/sysImport.jsp\");</script>");
			return;
		} catch (Exception e) {
			out.println("<script>alert(\"File not saving correctly!\");window.location.replace(\"webpages/admin/sysImport.jsp\");</script>");
			return;
		}
	}
	
	private static void createTempFolders(){
		File tmp = new File("C:\\tmp");
		File tmpFinal = new File("C:\\tmpFinal");
		
		if(!tmp.exists()){
			makeFolder(tmp);
		}
		if(!tmpFinal.exists()){
			makeFolder(tmpFinal);
		}
	}
	
	private static void makeFolder(File folderName){
		folderName.mkdir();
	}

	private static void loadExcelFile(String fileLocation, PrintWriter out) {
		try {
			String fileExtension = FilenameUtils.getExtension(fileLocation);
			
			if(fileExtension.equals("xls"))
				excelData = new HSSFWorkbook(new FileInputStream(fileLocation));
			else if(fileExtension.equals("xlsx"))
				excelData = new XSSFWorkbook(new FileInputStream(fileLocation));
			else{
				out.println("<script>alert(\"Invalid file type!\");window.location.replace(\"webpages/admin/sysImport.jsp\");</script>");
			}
			
		} catch (IOException e) {
			System.out.println("Can't load file!");
			return;
		}
	}

	private static void generateDatabase() {
		List<DatasetEntity> failureClasses = FailureClassConfig.parseExcelData(excelData.getSheetAt(2));
		List<DatasetEntity> eventCauses = EventCauseConfig.parseExcelData(excelData.getSheetAt(1));
		List<DatasetEntity> ueTypes = UETypeConfig.parseExcelData(excelData.getSheetAt(3));
		List<DatasetEntity> mcc_mncs = MCC_MNCConfig.parseExcelData(excelData .getSheetAt(4));
		ErrorEventConfig.parseExcelData(excelData.getSheetAt(0), eventCauses, failureClasses, mcc_mncs, ueTypes);
	}
}