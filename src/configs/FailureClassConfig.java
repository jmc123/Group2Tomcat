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
import entity.FailureClass;

public class FailureClassConfig {
	private static List<DatasetEntity> failureClasses;
	
	public static List<DatasetEntity> parseExcelData(Sheet excelSheet){	
		failureClasses = new ArrayList<>();
		Iterator<Row> rowIterator = excelSheet.iterator();
        List<Row> rowList = Lists.newArrayList(rowIterator);
	        
        for(int i = 1; i < rowList.size(); i++){
        	Iterator<Cell> cellIterator = rowList.get(i).cellIterator();
        	parseCells(cellIterator);
        }
		
		int objectsPersisted = PersistenceUtil.persistMany(failureClasses);
		System.out.println(objectsPersisted + " FailureClasses added to database.");
		return failureClasses;
	}

	private static void parseCells(Iterator<Cell> cellIterator){
		int failureClass;
		String desc;
    	
		failureClass = (int) cellIterator.next().getNumericCellValue();
    	desc = cellIterator.next().getStringCellValue();
    	
    	createFailureClass(failureClass, desc);
	}
	
	private static void createFailureClass(int failureClass, String desc){
		failureClasses.add(new FailureClass(failureClass, desc));
	}

	public static int numberOfFailureClasses() {
		return failureClasses.size();
	}
}