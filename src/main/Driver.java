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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import persistence.PersistenceUtil;

import com.google.common.collect.Lists;

import configs.ErrorEventConfig;
import configs.EventCauseConfig;
import configs.FailureClassConfig;
import configs.MCC_MNCConfig;
import configs.UETypeConfig;

/**
 * @author Group2<br>
 * 
 * Servlet called by webapp.<br>
 * Uploads file to local storage.<br>
 * Parses the file into the database.<br> 
 */
@SuppressWarnings({"serial", "unchecked"})
public class Driver {//extends HttpServlet {

	private static DecimalFormat dFormatter = new DecimalFormat("#,###,###");
	private static Workbook excelData;
//	private boolean isMultipart;
//	private String filePath;
//	private int maxFileSize = 10*200*1024;
//	private int maxMemSize = 4*1024;
	
	public static void main(String[] args){
		try{
			OPCPackage pkg = OPCPackage.open(new File("src/res/TestDataset.xlsx"));
			excelData = new XSSFWorkbook(pkg);
			pkg.close();
		} catch (Exception e){
			
		}
		PersistenceUtil.setDatabaseState(true);
		generateDatabase();
	}
	
//	/**
//	 * Initializes context-parameter to get file-path.
//	 */
//	public void init(){
//		filePath = getServletContext().getInitParameter("ImportFile");
//	}
//
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		
//		long startTime = System.nanoTime();
//		System.out.println("Filepath: " + filePath);
//		
//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html");
//		isMultipart = ServletFileUpload.isMultipartContent(request);
//		
//		if(!isMultipart){
//			out.println("<HTML><HEAD><TITLE>Servlet Upload</TITLE></HEAD>)"
//					 +  "<BODY><CENTER><p>No file uploaded!</p></CENTER></BODY></HTML>");
//			return;
//		}
//		
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		factory.setSizeThreshold(maxMemSize);
//		factory.setRepository(new File("/home/Dataset/"));
//		
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		upload.setSizeMax(maxFileSize);
//		File file = null;
//		
//		try{			
//			List<FileItem> fileItems = Lists.newArrayList(upload.parseRequest(request).iterator());
//			
//			out.println("<HTML><HEAD><TITLE>Excel Upload</TITLE></HEAD><BODY><CENTER>");
//			
//			for(FileItem fi : fileItems){
//				if(!fi.isFormField()){
//					String fileName = fi.getName();
//					
//					if(fileName.lastIndexOf("\\") >= 0)
//						file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")));
//					else
//						file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")+1));
//					
//					fi.write(file);
//					out.println("Uploaded File: <B>" + fileName + "</B><BR />");
//				}
//			}
//			loadExcelFile(file.getAbsolutePath(), out);
//
//			PersistenceUtil.setDatabaseState(true);
//			PersistenceUtil.dropSecondaryTables();
//			generateDatabase();
//			long timeTakenInMillis = (System.nanoTime()-startTime)/1000000;
//
//			out.println("<p>Successfully imported the database.</p>"
//				+ "<p>" + dFormatter.format(ErrorEventConfig.errorEvents.size())+ " ErrorEvents added to database."
//					+ "(Total: "+ dFormatter.format(PersistenceUtil.numberOfErrorEvents()) + ")"
//				+ "<br />"
//				+ dFormatter.format(ErrorEventConfig.invalidErrorEvents.size()) + " ErrorEvents removed due to inconsistencies."
//					+ "(Total: " + dFormatter.format(PersistenceUtil.numberOfInvalidErrorEvents()) + ")</p>"
//				+ "<p>Click <a href=\"queries.jsp\">here</a> to query the database.</p>"
//				+ "</CENTER></BODY>"
//				+ "<DIV style=\"position: relative\""
//				+ "<p style=\"position: fixed; bottom: 0; width=100%; text-align: center\"></p>"
//				+ "<p><CENTER>Dataset imported in " + String.format("%.2f", (double) timeTakenInMillis/1000) +" seconds</CENTER?></p>"
//				+ "</DIV></CENTER></BODY></HTML>");
//			out.close();
//			
//		} catch(Exception e){
//			out.println("<script>alert(\"Invalid file!\");window.location.replace(\"import.jsp\");</script>");
//			return;
//		}
//	}
//
//	private static void loadExcelFile(String fileLocation, PrintWriter out) {
//		try {
//			String fileExtension = FilenameUtils.getExtension(fileLocation);
//			
//			if(fileExtension.equals("xls"))
//				excelData = new HSSFWorkbook(new FileInputStream(fileLocation));
//			else if(fileExtension.equals("xlsx"))
//				excelData = new XSSFWorkbook(new FileInputStream(fileLocation));
//			else{
//				out.println("<script>alert(\"Invalid file type!\");window.location.replace(\"import.jsp\");</script>");
//			}
//			
//		} catch (IOException e) {
//			System.out.println("Can't load file!");
//			return;
//		}
//	}

	private static void generateDatabase() {
		List<Object> eventCauses = EventCauseConfig.parseExcelData(excelData.getSheetAt(1));
		List<Object> failureClasses = FailureClassConfig.parseExcelData(excelData.getSheetAt(2));
		List<Object> ueTypes = UETypeConfig.parseExcelData(excelData.getSheetAt(3));
		List<Object> mcc_mncs = MCC_MNCConfig.parseExcelData(excelData .getSheetAt(4));
		ErrorEventConfig.parseExcelData(excelData.getSheetAt(0), eventCauses, failureClasses, mcc_mncs, ueTypes);
//		ErrorEventConfig.parseExcelData(excelData.getSheetAt(0), null, null, null, null);
		System.out.println(">> Finished");
	}
}