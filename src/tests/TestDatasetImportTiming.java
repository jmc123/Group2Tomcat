package tests;

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

public class TestDatasetImportTiming {
	private static final String EXCEL_FILE = "src/res/TestLargeDataset.xlsx";
	private static XSSFWorkbook excelData;

	@BeforeClass
	public static void setUp(){
		PersistenceUtil.useTestDatabase();
	}
	
	@Test(timeout=120000)
	public void test30kRecordsImport() throws InvalidFormatException, IOException{
		OPCPackage pkg = OPCPackage.open(new File(EXCEL_FILE));
		excelData = new XSSFWorkbook(pkg);
		pkg.close();
		
		List<DatasetEntity> eventCauses = EventCauseConfig.parseExcelData(excelData.getSheetAt(1));
		List<DatasetEntity> failureClasses = FailureClassConfig.parseExcelData(excelData.getSheetAt(2));
		List<DatasetEntity> ueTypes = UETypeConfig.parseExcelData(excelData.getSheetAt(3));
		List<DatasetEntity> mcc_mncs = MCC_MNCConfig.parseExcelData(excelData .getSheetAt(4));
		CallFailureConfig.parseExcelData(excelData.getSheetAt(0), eventCauses, failureClasses, mcc_mncs, ueTypes);
	}
}
