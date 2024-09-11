package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class jscheck {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("C://Users//arockia.p.stephen//Downloads//download.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		DataFormatter formatter = new DataFormatter();
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> row = sheet.iterator();
		int totalrowcount = sheet.getPhysicalNumberOfRows();
		System.out.println(totalrowcount);
		int i=0;
		int rownumber = 0;
		while(i<totalrowcount)
			{
			Row sprow = row.next();
			Iterator<Cell> cell = sprow.cellIterator();
			while(cell.hasNext())
			{
				
				Cell value = cell.next();
				String name;
				
				if(value.getCellType()==CellType.STRING)
				{
					name = value.getStringCellValue();
					System.out.println(name);
					if(name.equalsIgnoreCase("Apple"))
					{
						rownumber = i;
					}
				}
				else
				{
					name = formatter.formatCellValue(value);
					System.out.println(name);
				}
				
				
				
			}
			i++;
			}
		System.out.println(rownumber);
		
	}

	}


