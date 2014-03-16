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
import entity.MCC_MNC;

public class MCC_MNCConfig {
	private static List<DatasetEntity> mcc_mncs;
	
	public static List<DatasetEntity> parseExcelData(Sheet excelSheet){
		mcc_mncs = new ArrayList<>();
        Iterator<Row> rowIterator = excelSheet.iterator();
        List<Row> rowList = Lists.newArrayList(rowIterator);
	        
        for(int i = 1; i < rowList.size(); i++){
            Iterator<Cell> cellIterator = rowList.get(i).cellIterator();
            parseCells(cellIterator);
        }
		
        PersistenceUtil.persistMany(mcc_mncs);;
		return mcc_mncs;
	}

	private static void parseCells(Iterator<Cell> cellIterator) {
		int mcc;
		int mnc;
		String country;
		String operator;
		
		mcc = (int) cellIterator.next().getNumericCellValue();
		mnc = (int) cellIterator.next().getNumericCellValue();
		country = cellIterator.next().getStringCellValue();
		operator = cellIterator.next().getStringCellValue();
		
		createMCC_MNC(mcc, mnc, country, operator);
	}
	
	private static void createMCC_MNC(int mcc, int mnc, String country, String operator){
		mcc_mncs.add(new MCC_MNC(mcc, mnc, country, operator));
	}

	public static int numberOfMCC_MNCs() {
		return mcc_mncs.size();
	}
}