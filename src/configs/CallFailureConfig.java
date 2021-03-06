package configs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import persistence.PersistenceUtil;

import com.google.common.collect.Lists;

import entity.DatasetEntity;
import entity.CallFailure;
import entity.EventCause;
import entity.FailureClass;
import entity.InvalidCallFailure;
import entity.MCC_MNC;
import entity.UEType;

public class CallFailureConfig {
	private static List<DatasetEntity> callFailures;
	private static List<DatasetEntity> invalidCallFailures;
	private static List<DatasetEntity> mcc_mncs;
	private static List<DatasetEntity> eventCauses;
	private static List<DatasetEntity> failureClasses;
	private static List<DatasetEntity> ueTypes;
	private static DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public static void parseExcelData(Sheet excelSheet, List<DatasetEntity> eventCausesInput,
															List<DatasetEntity> failureClassesInput,
															List<DatasetEntity> mcc_mncsInput,
															List<DatasetEntity> ueTypesInput){
		callFailures = new ArrayList<>();
		invalidCallFailures = new ArrayList<>();
		eventCauses = eventCausesInput;
		failureClasses = failureClassesInput;
		mcc_mncs = mcc_mncsInput;
		ueTypes = ueTypesInput;
        Iterator<Row> rowIterator = excelSheet.iterator();
        List<Row> rowList = Lists.newArrayList(rowIterator);
        
        for(int i = 1; i < rowList.size(); i++){
            Iterator<Cell> cellIterator = rowList.get(i).cellIterator();
            parseCells(cellIterator);	
        } 
		
		addFailuresToDB();
	}

	private static void parseCells(Iterator<Cell> cellIterator) {
		Date date;
		int eventId;
		int ueType;
		int market;
		int operator;
		int cellId;
		int duration;
		int failureClass = -1;
		int causeCode = -1;
		String invalidFailureClass = "";
		String invalidCauseCode = "";
		String neVersion;
		long imsi;
		long hier3_id;
		long hier32_id;
		long hier321_id;
		Cell next;
		int nextType;
		
		boolean valid = true;
		
		date = cellIterator.next().getDateCellValue();
		
		if(!isDateValid(date))
			valid = false;
		
		eventId = (int) cellIterator.next().getNumericCellValue();
		
		next = cellIterator.next();
		nextType = next.getCellType();
		
		if(nextType == Cell.CELL_TYPE_STRING){
			invalidFailureClass = next.getStringCellValue();
			valid = false;
		}
		else{
			failureClass = (int) next.getNumericCellValue();
			if(!valid || !isFailureClassValid(failureClass)){
				valid = false;
				invalidFailureClass = String.valueOf(failureClass);
			}
		}
		
		ueType = (int) cellIterator.next().getNumericCellValue();
		
		if(!valid || !isUETypeValid(ueType))
			valid = false;
		
		market = (int) cellIterator.next().getNumericCellValue();
		operator = (int) cellIterator.next().getNumericCellValue();
		
		if(!valid || !isMarketOperatorValid(market, operator))
			valid = false;	
		
		cellId = (int) cellIterator.next().getNumericCellValue();
		duration = (int) cellIterator.next().getNumericCellValue();
		
		next = cellIterator.next();
		nextType = next.getCellType();
		
		if(nextType == Cell.CELL_TYPE_STRING){
			invalidCauseCode = next.getStringCellValue();
			valid = false;
		}
		else{
			causeCode = (int) next.getNumericCellValue();
			
			if(!valid || !isEventCauseValid(eventId, causeCode)){
				valid = false;
				if(invalidFailureClass.equals(""))
						invalidFailureClass = String.valueOf(failureClass);
				invalidCauseCode = String.valueOf(causeCode);
			}
		}

		if(!valid || !isEventCauseValid(eventId, causeCode))
			valid = false;
		
		neVersion = cellIterator.next().getStringCellValue();
		imsi = (long) cellIterator.next().getNumericCellValue();
		hier3_id = (long) cellIterator.next().getNumericCellValue();
		hier32_id = (long) cellIterator.next().getNumericCellValue();
		hier321_id = (long) cellIterator.next().getNumericCellValue();
		
		if(valid){
			createCallFailure(date, eventId, failureClass, ueType, market, operator, cellId, duration, causeCode, neVersion, imsi, hier3_id, hier32_id, hier321_id);
		} else{
			createInvalidCallFailure(date, eventId, invalidFailureClass, ueType, market, operator, cellId, duration, invalidCauseCode, neVersion, imsi, hier3_id, hier32_id, hier321_id);
		}
	}
	
	private static boolean isDateValid(Date date){
		String dateString = dateFormatter.format(date);
		
		int day = Integer.parseInt(dateString.substring(0, 2));
		int month = Integer.parseInt(dateString.substring(3, 5));
		int year = Integer.parseInt(dateString.substring(6, 10));
		int hour = Integer.parseInt(dateString.substring(11, 13));
		int min = Integer.parseInt(dateString.substring(14, 16));
		
		if(day < 1 || day > 31){
			return false;
		}
		if(month < 1 || month > 12){
			return false;
		}
		if(year < 2000){
			return false;
		}
		if(hour < 0 || hour > 23){
			return false;
		}
		if(min < 0 || min > 59){
			return false;
		}
		
		//April, June, September, November
		if((month == 4 || month == 6 || month == 9 || month == 9) && (day > 30)){
			return false;
		}
		
		//February
		if(month == 2){
			if(isLeapYear(year) && day > 29){
				return false;
			}
			if(day > 28){
				return false;
			}
		}
		return true;
	}
	
	private static boolean isLeapYear(int year){
		return ((year%4 == 0) && (year%100 != 0) || (year%400 == 0));
	}

	private static boolean isFailureClassValid(int failureClass) {
		for(Object obj : failureClasses){
			FailureClass fc = (FailureClass) obj;
			
			if(fc.getFailureClass() == failureClass){
				return true;
			}
		}
		return false;
	}
	
	private static boolean isUETypeValid(int ueType) {
		for(Object obj : ueTypes){
			UEType uet = (UEType) obj;
			
			if(uet.getTac() == ueType){
				return true;
			}
		}
		return false;
	}

	private static boolean isMarketOperatorValid(int market, int operator) {	
		for(Object obj : mcc_mncs){
			MCC_MNC mcc_mnc = (MCC_MNC) obj;
			
			if(mcc_mnc.getMcc() == market && mcc_mnc.getMnc() == operator){
				return true;
			}
		}
		return false;
	}
	
	private static boolean isEventCauseValid(int eventId, int causeCode) {
		for(Object obj : eventCauses){
			EventCause eventCause = (EventCause) obj;
			
			if(eventCause.getEventId() == eventId && eventCause.getCauseCode() == causeCode){
				return true;
			}
		}
		return false;
	}

	private static void createCallFailure(Date date, int eventId, int failureClassId, int ueTypeId, int market, int operator,
								   int cellId, int duration, int causeCode, String neVersion,
								   long imsi, long hier3_id, long hier32_id, long hier321_id){
		
		FailureClass failureClass = PersistenceUtil.getFailureClass(failureClassId);
		UEType ueType = PersistenceUtil.getUEType(ueTypeId);
		EventCause event = PersistenceUtil.getEventCauseByEventIdAndCauseCode(eventId, causeCode);
		MCC_MNC mcc_mnc = PersistenceUtil.getMCC_MNCByMCCAndMNC(market, operator);	
		
		callFailures.add(new CallFailure(date, event, failureClass, ueType, mcc_mnc, cellId, duration,
									   neVersion, imsi, hier3_id, hier32_id, hier321_id));
	}
	
	private static void createInvalidCallFailure(Date date, int eventId, String failureClass, int ueType, int market, int operator,
			   									 int cellId, int duration, String causeCode, String neVersion,
			   									 long imsi, long hier3_id, long hier32_id, long hier321_id){
		invalidCallFailures.add(new InvalidCallFailure(date, eventId, failureClass, ueType, market, operator, cellId, duration, causeCode,
					   								 neVersion, imsi, hier3_id, hier32_id, hier321_id));
	}
	
	private static void addFailuresToDB(){
		PersistenceUtil.persistManyFailures(callFailures);
		PersistenceUtil.persistManyFailures(invalidCallFailures);
	}
	
	public static int numberOfCallFailures(){
		return callFailures.size();
	}
	
	public static int numberOfInvalidCallFailures(){
		return invalidCallFailures.size();
	}
	
	public static List<DatasetEntity> getInvalidFailures(){
		return invalidCallFailures;
	}
}