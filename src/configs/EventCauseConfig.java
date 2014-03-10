package configs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import persistence.PersistenceUtil;

import com.google.common.collect.Lists;

import entity.DatasetEntity;
import entity.EventCause;

public class EventCauseConfig {
	private static List<DatasetEntity> eventCauses;
	
	public static List<DatasetEntity> parseExcelData(Sheet excelSheet){
		eventCauses = new ArrayList<>();
		Iterator<Row> rowIterator = excelSheet.iterator();
        List<Row> rowList = Lists.newArrayList(rowIterator);
	        
        for(int i = 1; i < rowList.size(); i++){
        	Iterator<Cell> cellIterator = rowList.get(i).cellIterator();
        	parseCells(cellIterator);
        }
		
		int objectsPersisted = PersistenceUtil.persistMany(eventCauses);
		System.out.println(objectsPersisted + " EventCauses added to database.");
		return eventCauses;
	}
	
	private static void parseCells(Iterator<Cell> cellIterator){
		int causeCode;
		String desc;
		int eventId;
		
		causeCode = (int) cellIterator.next().getNumericCellValue();
    	eventId = (int) cellIterator.next().getNumericCellValue();
    	desc = cellIterator.next().getStringCellValue();
    	
    	createEventCause(causeCode, eventId, desc);
	}
	
	private static void createEventCause(int causeCode, int eventId, String desc){
		eventCauses.add(new EventCause(causeCode, eventId, desc));
	}
	
	public static int numberOfEventCauses(){
		return eventCauses.size();
	}
}
