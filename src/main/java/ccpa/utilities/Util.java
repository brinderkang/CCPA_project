package ccpa.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import ccpa.base.Baseclass;

public class Util extends Baseclass {
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=10;

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

}
