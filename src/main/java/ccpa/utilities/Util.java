package ccpa.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import ccpa.base.Baseclass;

public class Util extends Baseclass {
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=10;
	public static String TESTDATA_SHEET_PATH=System.getProperty("user.dir")+"\\src\\main\\java\\ccpa\\testdata\\TestDataCCPA.xlsx";
	static Xlreader xls;
	public static String urlutil;
	
	public Util(WebDriver driver)
	{
		this.driver=driver;
	}

	public void draganddrop(WebElement source,WebElement dest)
	{
		//Using Action class for drag and drop.		
        Actions act=new Actions(driver);					

	//Dragged and dropped.		
        act.dragAndDrop(source, dest).build().perform();
		
	}
	public void switchToFrame(int frameid)
	{
		driver.switchTo().frame(frameid);
	}
	
	public void switchToFrame(String frameid)
	{
		driver.switchTo().frame(frameid);
	}
	
	public void getDropdownValue(List<WebElement> listele, String actualVal)
	{
		for(WebElement ele:listele) 
		{
			String Val=ele.getText().trim();
			if(Val.equalsIgnoreCase(actualVal))
			{
				ele.click();
			}
		}
	}
	
	public void getDropdownInnerHTML(List<WebElement> listele, String actualVal) throws InterruptedException
	{
		for(WebElement ele:listele)
		{
			String Val=ele.getAttribute("innerHTML").trim();
			Thread.sleep(2000);
			if(Val.equalsIgnoreCase(actualVal))
			{
				ele.click();
				return;
			}
		}
	}
	
	public static Integer getCookiesinfo()
	{
		Set<Cookie> allcookies= driver.manage().getCookies();
		int cookiescount = allcookies.size();
		for(Cookie cookie:allcookies)
		{
			System.out.println("Name : "+ cookie.getName());
			System.out.println("Domain : "+ cookie.getDomain());
			System.out.println("Path : "+ cookie.getPath());
			System.out.println("Value : "+ cookie.getValue());
			System.out.println("-------------------------------------");
		}
		return cookiescount;
	}
	
	// Get Date and Time of system
		public String Fn_DateTime(){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();		
			String TempFileName=dateFormat.format(date);
			String NewFileName1=TempFileName.replace("/","_");
			String NewFileName2=NewFileName1.replace(" ","_");
			String NewFileName=NewFileName2.replace(":","_");
			return NewFileName;
//			System.out.println(NewFileName);
		}
	
//	take screen shot of failed test case
	public void takeScreenShot(ITestResult result){

		if(ITestResult.FAILURE== result.getStatus())
		{
		try{
		TakesScreenshot ts= (TakesScreenshot)driver;

		File source=ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(source, new File(System.getProperty("user.dir")+"/src/main/java/ccpa/screenshots/"+result.getName()+Fn_DateTime()+".png"));

//		FileHandler.copy(source, new File("./screenshot/"+result.getName()+".png"));
		}
		catch(Exception e)
		{
		System.out.println("Exception while taking screenshot");
		}

		}
		}
	
//	Fetch one dimension test data from excel file
	public static Object[][] getTestDataUrl(String sheetname) throws IOException
	{
		System.out.println(TESTDATA_SHEET_PATH);
		xls=new Xlreader(TESTDATA_SHEET_PATH);
		int rowcount=xls.getRowCount(sheetname);
		int cellcount=xls.getCellCount(sheetname, 0);
		int rows=0;
		int x=0;
		for(int y=0;y<=rowcount;y++)
		{
			String tRun=xls.getCellData(sheetname, "RUN", y);
			if(tRun.equalsIgnoreCase("ON"))
			{
				rows=rows+1;
			}
		}
		
		Object[][] data = new Object[rows][1];
		data = new Object[rows][cellcount];
		for(int i=0;i<=rowcount;i++)
			{
			String tRun=xls.getCellData(sheetname, "RUN", i);
			if(tRun.equalsIgnoreCase("ON"))			
				{
							
				for(int k=0;k<cellcount;k++)
					{
						data[x][k]=xls.getCellData(sheetname, i, k).trim();
						urlutil=xls.getCellData(sheetname, i, 0);
						System.out.println(data[x][k]);						
				 
					}
				x =x+1;
				}
			}
		return data;
		
	}

}
