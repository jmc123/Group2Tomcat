package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.PersistenceUtil;
import configs.CallFailureConfig;
import configs.EventCauseConfig;
import configs.FailureClassConfig;
import configs.MCC_MNCConfig;
import configs.UETypeConfig;
import entity.DatasetEntity;

@SuppressWarnings("unused")
public class TestDatasetImportParsing {

	private static final String EXCEL_FILE = "src/res/TestDataset.xlsx";
	private static List<DatasetEntity> eventCauses, failureClasses, mcc_mncs, uetypes;
	private static XSSFWorkbook excelData;
	
	@BeforeClass
	public static void setUp() throws InvalidFormatException, IOException{
		PersistenceUtil.useTestDatabase();

		OPCPackage pkg = OPCPackage.open(new File(EXCEL_FILE));
		excelData = new XSSFWorkbook(pkg);
		pkg.close();
		
		CallFailureConfig eec = new CallFailureConfig();
		EventCauseConfig ecc = new EventCauseConfig();
		FailureClassConfig fcc = new FailureClassConfig();
		MCC_MNCConfig mcc_mncc = new MCC_MNCConfig();
		UETypeConfig uetc = new UETypeConfig();
	}
	
	@Test
	public void testEventCauses(){
		eventCauses = EventCauseConfig.parseExcelData(excelData.getSheetAt(1));
		assertEquals("There should be 80 EventCauses", 80, EventCauseConfig.numberOfEventCauses());
	}
	
	@Test
	public void testFailureClasses(){
		failureClasses = FailureClassConfig.parseExcelData(excelData.getSheetAt(2));
		assertEquals("There should be 5 FailureClasses", 5, FailureClassConfig.numberOfFailureClasses());
	}
	
	@Test
	public void testMCC_MNCs(){
		mcc_mncs = MCC_MNCConfig.parseExcelData(excelData.getSheetAt(4));
		assertEquals("There should be 41 MCC_MNCs", 41, MCC_MNCConfig.numberOfMCC_MNCs());
	}
	
	@Test
	public void testUETypes(){
		uetypes = UETypeConfig.parseExcelData(excelData.getSheetAt(3));
		assertEquals("There should be 99 UETypes", 99, UETypeConfig.numberOfUETypes());		
	}
	
	@Test
	public void testErrorEvents() {
		CallFailureConfig.parseExcelData(excelData.getSheetAt(0), eventCauses, failureClasses, mcc_mncs, uetypes);
		assertEquals("There should be 80 CallFailures", 80, CallFailureConfig.numberOfCallFailures());
		assertEquals("There should be 20 InvalidCallFailures", 20, CallFailureConfig.numberOfInvalidCallFailures());
	}
}
