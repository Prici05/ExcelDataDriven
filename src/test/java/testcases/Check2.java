package testcases;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Check2 {
	
	@Test(dataProvider="testdata")
	public void check(String greeting, String name, String id)
	{
		System.out.println(greeting+name+id);
	}

	
	@DataProvider(name="testdata")
	public Object[][] testdata() throws IOException
	{
//		Object[][] data = {{"hello", "prici", "1"}, {"Hi", "Arockia", "2"}, {"Hey", "Stephen", "3"}};
		
		FileInputStream fis = new FileInputStream("C://Users//arockia.p.stephen//eclipse-workspace//ExcelDataDriven//src//test//java//testcases//exceldata.xlsx");
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    
	    DataFormatter formatter = new DataFormatter();
	    
	    XSSFSheet sheet = workbook.getSheet("check1");
	    
	    int rowcount = sheet.getPhysicalNumberOfRows();
	    int colcount = sheet.getRow(0).getLastCellNum();
	    System.out.println(rowcount);
	    System.out.println(colcount);
	    
	    Object[][] data = new Object[rowcount-1][colcount];
	    
	    for(int i=0;i<rowcount-1;i++)
	    {
	    	XSSFRow row = sheet.getRow(i+1);
	    	for(int j=0;j<colcount;j++)
	    	{
	    		XSSFCell cell = row.getCell(j);
	    		data[i][j]=formatter.formatCellValue(cell);
	    	}
	    }
	    
	    return data;
	    
	
	
	
	
	}
	
}
