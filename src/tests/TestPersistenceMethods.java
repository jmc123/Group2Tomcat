package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
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
import entity.User;

public class TestPersistenceMethods {
	private static final String EXCEL_FILE = "src/res/TestDataset.xlsx";
	private static XSSFWorkbook excelData;

	@BeforeClass
	public static void setUp() throws InvalidFormatException, IOException {
		PersistenceUtil.useTestDatabase();
		OPCPackage pkg = OPCPackage.open(new File(EXCEL_FILE));
		excelData = new XSSFWorkbook(pkg);
		pkg.close();

		List<DatasetEntity> eventCauses = EventCauseConfig
				.parseExcelData(excelData.getSheetAt(1));
		List<DatasetEntity> failureClasses = FailureClassConfig
				.parseExcelData(excelData.getSheetAt(2));
		List<DatasetEntity> ueTypes = UETypeConfig.parseExcelData(excelData
				.getSheetAt(3));
		List<DatasetEntity> mcc_mncs = MCC_MNCConfig.parseExcelData(excelData
				.getSheetAt(4));
		ErrorEventConfig.parseExcelData(excelData.getSheetAt(0), eventCauses,
				failureClasses, mcc_mncs, ueTypes);
	}

	@Test
	public void testPersist() {
		long old = PersistenceUtil.findAllUsers().size();
		PersistenceUtil.persist(new User("userPersistTest", DigestUtils
				.sha1Hex("pass"), 1, "Test", "User", "test@email.com",
				"01234567"));
		long diff = PersistenceUtil.findAllUsers().size() - old;
		assertEquals("Should be true", true, (diff > 0));
	}

	@Test
	public void testRemove() {
		long old = PersistenceUtil.findAllUsers().size();
		PersistenceUtil.remove(PersistenceUtil
				.getUserByUsername("userPersistTest"));
		long diff = old - PersistenceUtil.findAllUsers().size();
		assertEquals("Should be true", true, (diff > 0));
	}

	@Test
	public void testNumberOfErrorEvents() {
		assertEquals("There should be 24160 ErrorEvents", 24160,
				PersistenceUtil.numberOfErrorEvents());
	}

	@Test
	public void testNumberOfInvalidErrorEvents() {
		assertEquals("There should be 6040 InvalidErrorEvents", 6040,
				PersistenceUtil.numberOfInvalidErrorEvents());
	}

	@Test
	public void testGetFailureClass() {
		assertEquals("Should be 'EMERGENCY'", "EMERGENCY", PersistenceUtil
				.getFailureClass(0).getDesc());
	}

	@Test
	public void testGetUEType() {
		assertEquals("Should be 'G410'", "G410",
				PersistenceUtil.getUEType(100100).getModel());
	}

	@Test
	public void testGetUserType() {
		assertEquals("Should be 'System Administrator'",
				"System Administrator", PersistenceUtil.getUserTypeById(1)
						.getDesc());
	}

	@Test
	public void testGetEventCause() {
		assertEquals("Should be 'RRC CONN SETUP-SUCCESS'",
				"RRC CONN SETUP-SUCCESS", PersistenceUtil
						.getEventCauseByEventIdAndCauseCode(4097, 0).getDesc());
	}

	@Test
	public void testGetMCC_MNC() {
		assertEquals("Should be 'TDC-DK'", "TDC-DK", PersistenceUtil
				.getMCC_MNCByMCCAndMNC(238, 1).getOperator());
	}

	@Test
	public void testGetUserByUsername() {
		assertEquals("Should be 'lname@email.com'", "lname@email.com",
				PersistenceUtil.getUserByUsername("user").getEmailAddress());
	}

	@Test
	public void testFindAllUserNames() {
		assertEquals("Should be 'user'", "user", PersistenceUtil
				.findAllUserNames().get(0));
	}

	@Test
	public void testFindAllUsers() {
		assertEquals("Should be '1'", 1, PersistenceUtil.findAllUsers().get(0)
				.getUserType().getId());
	}

	@Test
	public void testRegisterUser() {
		int old = PersistenceUtil.findAllUsers().size();
		PersistenceUtil.registerUser("userTest", DigestUtils.sha1Hex("pass"),
				1, "Test", "User", "test@email.com", "01234567");
		int diff = PersistenceUtil.findAllUsers().size() - old;
		assertEquals("Should be true", true, (diff > 0));
	}
}