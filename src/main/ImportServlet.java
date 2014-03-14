package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.common.collect.Lists;

/**
 * @author Group2<br>
 * 
 * Servlet called by webapp.<br>
 * Uploads file to local storage.<br>
 * Parses the file into the database.<br> 
 */
@SuppressWarnings({"serial", "unchecked"})
public class ImportServlet extends HttpServlet {
	private String filePath;
	private int maxFileSize = 100*200*1024;
	private int maxMemSize = 4*1024;
	
	private ImportServlet(){
		
	}
	
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
			
			for(FileItem fi : fileItems){
				if(!fi.isFormField()){
					String fileName = fi.getName();
					
					if(fileName.lastIndexOf("\\") >= 0)
						file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")));
					else
						file = new File(filePath+fileName.substring(fileName.lastIndexOf("\\")+1));
					
					fi.write(file);
				}
			}
			
			
			DatabaseGenerator.generateDatabase(file.getAbsolutePath(), out, (System.nanoTime()-startTime)/1000000);		
			out.println("<script>window.location.replace(\"webpages/admin/sysImport.jsp\");</script>");
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
		makeFolder(new File("C:\\tmp"));
		makeFolder(new File("C:\\tmpFinal"));
	}
	
	private static void makeFolder(File folderName){
		if(!folderName.exists()){
			folderName.mkdir();
		}
	}
}