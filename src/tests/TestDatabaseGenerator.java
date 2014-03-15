package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import main.DatabaseGenerator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.PersistenceUtil;

@SuppressWarnings("unused")
public class TestDatabaseGenerator {
	private static final String EXCEL_FILE_XLSX = "src/res/TestDataset.xlsx";
	private static final String EXCEL_FILE_XLS = "src/res/TestDataset.xls";
	private static final String EXCEL_FILE_INVALID = "src/res/TestDataset.invalid";
	private static XSSFWorkbook excelData;
	
	
	@BeforeClass
	public static void setUp() throws InvalidFormatException, IOException{
		PersistenceUtil.useTestDatabase();

		DatabaseGenerator dbG = new DatabaseGenerator();
	}

	@Test
	public void testDatabaseGeneratorXLSX() {
		DatabaseGenerator.generateDatabase(EXCEL_FILE_XLSX, new PrintWriter(new StringWriter()), 0L);
		assertEquals("Should be 'true'", true, DatabaseGenerator.datasetUploaded());
	}
	
	@Test
	public void testDatabaseGeneratorXLS() {
		DatabaseGenerator.generateDatabase(EXCEL_FILE_XLS, new PrintWriter(new StringWriter()), 0L);
		assertEquals("Should be 'true'", true, DatabaseGenerator.datasetUploaded());
	}
	
	@Test
	public void testDatabaseGeneratorInvalidFileType(){
		DatabaseGenerator.generateDatabase(EXCEL_FILE_INVALID, new PrintWriter(new StringWriter()), 0L);
		assertEquals("Should be 'true'", true, DatabaseGenerator.datasetUploaded());
	}
	
	@Test
	public void testDatabaseConfirmation(){
		DatabaseGenerator.generateDatabase(EXCEL_FILE_XLSX, new PrintWriter(new StringWriter()), 0L);
		DatabaseGenerator.datasetConfirmed();
		assertEquals("Should be 'false'", false, DatabaseGenerator.datasetUploaded());
	}
}
