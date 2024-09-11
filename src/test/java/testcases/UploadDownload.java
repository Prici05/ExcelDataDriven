

package testcases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;




public class UploadDownload {
	
	public static void main(String[] args) throws IOException
	{
		String fruitname="Banana";
		String columnname = "Price";
		String value = "500";
		String Filepath = "C://Users//arockia.p.stephen//Downloads//download.xlsx";
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
//		1. Download the file
		driver.findElement(By.id("downloadButton")).click();
		
//		2. Edit the excel file
		
		int rownumber = getRowNumber(Filepath, fruitname);
		int columnnumer = getColumnNumber(Filepath,columnname);
		Boolean check = updatevaluesincell(rownumber,columnnumer, value,Filepath);
		Assert.assertTrue(check);
		
//		3. Upload the edited file
		driver.findElement(By.id("fileinput")).sendKeys(Filepath);
		
//		4. verify the success message and wait for element to dispappear
		By toastlocator = By.cssSelector(".Toastify__toast-body div:nth-child(2)");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(toastlocator));
		String toastmessage = driver.findElement(toastlocator).getText();
		System.out.println(toastmessage);
		Assert.assertEquals("Updated Excel Data Successfully.", toastmessage);
		
//		5.verify if updated value is displaying in the webpage
		String Pricecolumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualprice = driver.findElement(By.xpath("//div[text()='"+fruitname+"']/parent::div/parent::div/div[@id='cell-"+Pricecolumn+"-undefined']")).getText();
		System.out.println(actualprice);
		
		
	}
	
	
	public static int getColumnNumber(String FilePath, String columnname) throws IOException
	{
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> row = sheet.iterator();
		 
		Row firstrow = row.next();
		Iterator<Cell> cell = firstrow.cellIterator(); 
		int k=0;
		int columnnumber=0;
		
		while(cell.hasNext())
		{
			
			Cell value = cell.next();
			if(value.getStringCellValue().equalsIgnoreCase(columnname))
			{
				columnnumber = k;
			}
			k++;
		}
		return columnnumber;
	  
	}
	
	public static int getRowNumber(String Filepath, String fruitname) throws IOException
	{
		FileInputStream fis = new FileInputStream("C://Users//arockia.p.stephen//Downloads//download.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> row = sheet.iterator();
		int totalrowcount = sheet.getPhysicalNumberOfRows();
		int i=0;
		int rownumber=0;
		while(i<totalrowcount)
			{
			Row sprow = row.next();
			Iterator<Cell> cell = sprow.cellIterator();
			while(cell.hasNext())
			{
				
				Cell value = cell.next();
				if(value.getCellType()==CellType.STRING && value.getStringCellValue().equalsIgnoreCase("Apple"))
				{
					rownumber = i;
				}

			}
			i++;
			}
		return rownumber;
		
	}
	
	public static boolean updatevaluesincell(int rownumber, int columnnumber, String value, String FilePath) throws IOException
	{
		FileInputStream fis = new FileInputStream("C://Users//arockia.p.stephen//Downloads//download.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row rowfield = sheet.getRow(rownumber);
		Cell cellfield = rowfield.getCell(columnnumber);
		cellfield.setCellValue(value);
		FileOutputStream fos = new FileOutputStream(FilePath);
		workbook.write(fos);
		workbook.close();
		return true;
	    
	}

}
