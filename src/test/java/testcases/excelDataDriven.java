package testcases;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class excelDataDriven {
	
	public ArrayList<String> getData(String testcasename) throws IOException
	{
//		1. create object for xssfworkbook object to take control of entire workbook
		FileInputStream fis = new FileInputStream("C://Users//arockia.p.stephen//eclipse-workspace//ExcelDataDriven//src//test//java//testcases//exceldata.xlsx");
//		fis is used to create object that holds the path of file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		ArrayList<String> array = new ArrayList<String>();
		
		
//		2. Get access to all sheets present in that workbook and go to specific sheet
		int totalsheets = workbook.getNumberOfSheets();
		for(int i=0;i<totalsheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("testdata"))
			{
				XSSFSheet sheet = workbook.getSheetAt(i);  
//		3. Get access to all rows of sheet
				Iterator<Row> row = sheet.iterator();  //Sheet is a collection of rows
				
//		4. Get access to specific row of all rows
				Row firstrow = row.next();
				
//		5. Get access to all cells of row
				Iterator<Cell> cell = firstrow.cellIterator();  // Row is a collection of cells
				int k=0;
				int column = 0;
				while(cell.hasNext())
				{
				   Cell value = cell.next();
				   if(value.getStringCellValue().equalsIgnoreCase("TestcaseName"))
				   {
					   column=k;
				   }
				   k++;
				   
				}
//				System.out.println(column);
				
				while(row.hasNext())
				{
					Row r = row.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename))
					{
						Iterator<Cell> cv = r.cellIterator();
						
						while(cv.hasNext())
							
						{
							Cell c = cv.next();
							if(c.getCellType()==CellType.STRING)
							{
								array.add(c.getStringCellValue());	
							}
							else
							{
							
//								System.out.println(cv.next().getStringCellValue());
								array.add(NumberToTextConverter.toText(c.getNumericCellValue()));	
							
						}
					}
				}
			}
		}
		}
		return array;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		

	}
}
				
				
				
		
		

	
		


