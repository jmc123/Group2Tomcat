package tests;

import java.io.File;
import java.io.IOException;

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

public class TestDatasetImportTiming {
	private static final String EXCEL_FILE = "src/res/TestLargeDataset.xlsx";
	private static XSSFWorkbook excelData;

	@BeforeClass
	public static void setUp() throws InvalidFormatException, IOException{
		PersistenceUtil.dropSecondaryTables();
	}
	
	@Test(timeout=120000)
	public void test30kRecordsImport(){
		try {
			OPCPackage pkg = OPCPackage.open(new File(EXCEL_FILE));
			excelData = new XSSFWorkbook(pkg);
			pkg.close();
		} catch (InvalidFormatException | IOException e) {
			System.out.println("Can't load database!");
			System.exit(-1);
		}
		ErrorEventConfig.parseExcelData(excelData.getSheetAt(0), null, null, null, null);
		EventCauseConfig.parseExcelData(excelData.getSheetAt(1));
		FailureClassConfig.parseExcelData(excelData.getSheetAt(2));
		UETypeConfig.parseExcelData(excelData.getSheetAt(3));
		MCC_MNCConfig.parseExcelData(excelData.getSheetAt(4));
	}
}
