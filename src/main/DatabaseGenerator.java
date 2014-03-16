package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import configs.CallFailureConfig;
import configs.EventCauseConfig;
import configs.FailureClassConfig;
import configs.MCC_MNCConfig;
import configs.UETypeConfig;
import entity.DatasetEntity;

public class DatabaseGenerator {
	private static Workbook excelData;
	private static boolean datasetUploaded = false;
	private static long totalTime;
	
	public static String fileName = null;
	
	private DatabaseGenerator(File uploadedFile){
		totalTime = 0;
		fileName = uploadedFile.getName();
		datasetUploaded = true;
	}
	
	public static void generateDatabase(File uploadedFile, PrintWriter out, long uploadTimeInMillis){
		long startTime = System.nanoTime();
		new DatabaseGenerator(uploadedFile);
		loadExcelFile(uploadedFile.getAbsolutePath(), out);
		generateDatabase();
		totalTime = ((System.nanoTime()-startTime)/1000000) + uploadTimeInMillis;
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
		CallFailureConfig.parseExcelData(excelData.getSheetAt(0), eventCauses, failureClasses, mcc_mncs, ueTypes);
	}
	
	public static boolean datasetUploaded(){
		return datasetUploaded;
	}
	
	public static long datasetConfirmed(){
		datasetUploaded = false;
		return totalTime;
	}
}