package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.PersistenceUtil;
import configs.ErrorEventConfig;
import configs.EventCauseConfig;
import configs.FailureClassConfig;
import configs.MCC_MNCConfig;
import configs.UETypeConfig;
import entity.DatasetEntity;

public class TestDatabaseQueries {
	private static final String EXCEL_FILE = "src/res/TestDataset.xlsx";
	private static XSSFWorkbook excelData;
	private SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@BeforeClass
	public static void setUp() throws InvalidFormatException, IOException{
		PersistenceUtil.useTestDatabase();
		OPCPackage pkg = OPCPackage.open(new File(EXCEL_FILE));
		excelData = new XSSFWorkbook(pkg);
		pkg.close();
		
		List<DatasetEntity> eventCauses = EventCauseConfig.parseExcelData(excelData.getSheetAt(1));
		List<DatasetEntity> failureClasses = FailureClassConfig.parseExcelData(excelData.getSheetAt(2));
		List<DatasetEntity> ueTypes = UETypeConfig.parseExcelData(excelData.getSheetAt(3));
		List<DatasetEntity> mcc_mncs = MCC_MNCConfig.parseExcelData(excelData .getSheetAt(4));
		ErrorEventConfig.parseExcelData(excelData.getSheetAt(0), eventCauses, failureClasses, mcc_mncs, ueTypes);
	}
	
	@Test
	public void testFindEventCauseByIMSI(){
		assertEquals("Should be '7'", 7, PersistenceUtil.findEventCauseByIMSI(310560000000012L).size());
	}
	
	@Test
	public void testFindUserByUserName(){
		assertEquals("Should be '1'", 1, PersistenceUtil.findUserByUsername("user").getUserType().getId());
	}
	
	@Test
	public void testFindNumberOfFailuresAndDuration() throws ParseException{
		assertEquals("Should be '0'", 0, PersistenceUtil.findNumberOfFailuresAndDuration(dateParser.parse(PersistenceUtil.returnDate("01-01-2013 00:00")), dateParser.parse(PersistenceUtil.returnDate("01-01-2014 00:00"))).size());
	}
	
	@Test
	public void testFindNumberOfFailuresByIMSI() throws ParseException{
		assertEquals("Should be '1'", 1, PersistenceUtil.findNumberOfFailures(310560000000012L, dateParser.parse(PersistenceUtil.returnDate("01-01-2013 00:00")), dateParser.parse(PersistenceUtil.returnDate("01-01-2014 00:00"))).size());
	}
	
	@Test
	public void testReturnDate(){
		assertEquals("Should be '01-01-2013 00:00:00'", "01-01-2013 00:00:00", PersistenceUtil.returnDate("01-01-2013 00:00"));
	}
	
	@Test
	public void testFindUniqueEventCauseAndOccurancesByModel(){
		assertEquals("Should be '8'", 8, PersistenceUtil.findUniqueEventCauseAndOccurancesByModel("VEA3").size());
	}
	
	@Test
	public void testFindCallFailuresBetweenDates() throws ParseException{
		assertEquals("Should be '0'", 0, PersistenceUtil.findCallFailuresBetweenDates(dateParser.parse(PersistenceUtil.returnDate("01-01-2013 00:00")), dateParser.parse(PersistenceUtil.returnDate("01-01-2014 00:00"))).size());
	}
	
	@Test
	public void testFindUniqueCauseByIMSI(){
		assertEquals("Should be '6'", 6, PersistenceUtil.findUniqueCauseByIMSI(310560000000012L).size());
	}
	
	@Test
	public void testFindNumberOfFailuresByModelOverTime() throws ParseException{
		assertEquals("Should be '1'", 1, PersistenceUtil.findNumberOfFailuresByModelOverTime("VEA3", dateParser.parse(PersistenceUtil.returnDate("01-01-2013 00:00")), dateParser.parse(PersistenceUtil.returnDate("01-01-2014 00:00"))).size());
	}
}
