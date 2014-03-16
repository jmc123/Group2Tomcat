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
import entity.UEType;

public class UETypeConfig {
	private static List<DatasetEntity> ueTypes;
	
	public static List<DatasetEntity> parseExcelData(Sheet excelSheet){
		ueTypes = new ArrayList<>();
		Iterator<Row> rowIterator = excelSheet.iterator();
        List<Row> rowList = Lists.newArrayList(rowIterator);
	        
        for(int i = 1; i < rowList.size(); i++){
            Iterator<Cell> cellIterator = rowList.get(i).cellIterator();
            parseCells(cellIterator);
        }
		
        PersistenceUtil.persistMany(ueTypes);
		return ueTypes;
	}

	private static void parseCells(Iterator<Cell> cellIterator) {
		int tac;
		String mName = "";
		String manu;
		String access;
		String model = "";
		String vName;
		String ueType;
		String os;
		String inputMode;
		
		Cell next;
		int nextType;
		
		tac = (int) cellIterator.next().getNumericCellValue();
		
		next = cellIterator.next();
		nextType = next.getCellType();
		
		if(nextType == Cell.CELL_TYPE_NUMERIC)
			mName = String.valueOf(next.getNumericCellValue());
		else if(nextType == Cell.CELL_TYPE_STRING)
			mName = next.getStringCellValue();
		
		manu = cellIterator.next().getStringCellValue();
		access = cellIterator.next().getStringCellValue();
		
		next = cellIterator.next();
		nextType = next.getCellType();
		
		if(nextType == Cell.CELL_TYPE_NUMERIC)
			model = String.valueOf(next.getNumericCellValue());
		else if(nextType == Cell.CELL_TYPE_STRING)
			model = next.getStringCellValue();
		
		vName = cellIterator.next().getStringCellValue();
		ueType = cellIterator.next().getStringCellValue();
		os = cellIterator.next().getStringCellValue();
		inputMode = cellIterator.next().getStringCellValue();
		
		
		createUEType(tac, mName, manu, access, model, vName, ueType, os, inputMode);
	}
	
	private static void createUEType(int tac, String mName, String manu, String access, String model, String vName,
									 String ueType, String os, String inputMode){
		ueTypes.add(new UEType(tac, mName, manu, access, model, vName, ueType, os, inputMode));
	}

	public static int numberOfUETypes() {
		return ueTypes.size();
	}
}