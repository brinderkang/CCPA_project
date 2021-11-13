package ccpa.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ccpa.base.Baseclass;
import ccpa.pages.Homepage;
import ccpa.pages.Homepage2;
import ccpa.utilities.Util;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class HomepageTestDP extends Baseclass {
	
	Homepage2 objhomepage;
	Util objutil;
	static Properties prop;
	WebDriver driver;
	public static ITestResult result;
	static ExtentTest test;
	static ExtentReports report;
	
	public HomepageTestDP()
	{
		super();
	}
	
	@BeforeClass
	public static void startTest()
	{
	report = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\ExtentReportss\\ExtentReportResult.html");
//	test = report.startTest("Launch URL : " + URL);
	}
	
	@BeforeTest
	public void setUp()
	{
		try {
			prop=new Properties();
			FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/ccpa/config/config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
//		String newurl=prop.getProperty("url1");
//		initialisation(newurl);
//		objhomepage = new Homepage();
//		objutil = new Util();
		
	}
	
	@DataProvider(name="geturls")
	public Object[][] getUrldata() throws IOException
	{
		Object data[][]=Util.getTestDataUrl("CCPAUrls");
		return data;
	}
	
	@Test(dataProvider="geturls",priority=1)
	public void LaunchUrl(String URL,String PAGETITLE, String RUN,String NAME, String Assert_val, ITestContext context) throws InterruptedException
	{
		test = report.startTest("Launch "+ NAME +" : " + URL);
		context.setAttribute("customTestName", URL);
//		initialisation(URL);
		System.setProperty("webdriver.chrome.driver","C:\\Users\\rahul.chadha\\Desktop\\BrowserDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		
//		String newurl=prop.getProperty("url1");
		try {
		System.out.println(URL +"  Launched");
		Thread.sleep(3000);
		if(!URL.contains("https://"))
		{
			URL="https://"+URL;
		}
		Thread.sleep(2000);
		driver.get(URL);
		objhomepage = new Homepage2(driver);
		objutil = new Util(driver);
//		try {
//			String title=objhomepage.Title();
//			Assert.assertEquals(title, PAGETITLE);
//			System.out.println("Title verified");
//		} catch (Exception e) {
//			objutil.takeScreenShot(result1);
//			System.out.println("Title not verified");
//			e.printStackTrace();
//		}
		
//		boolean flag=objhomepage.logo();
//		Assert.assertEquals(flag, true);
		
//		try {
			boolean flag1=objhomepage.cookiesContainer();
			boolean exp_val=Boolean.parseBoolean(Assert_val);
			Assert.assertEquals(flag1, exp_val);
//		} catch (Exception e) {
//			objutil.takeScreenShot(result1);
//			e.printStackTrace();
//		}
		
//		try {
			boolean flag2=objhomepage.uiCCPApopup();
//			boolean exp_val=Boolean.parseBoolean(Assert_val);
			Assert.assertEquals(flag2, exp_val);
//		} catch (Exception e) {
//			objutil.takeScreenShot(result1);
//			e.printStackTrace();
//		}
		
//		try {
			Integer flag3=objhomepage.saveCookiesPopup();
			System.out.println(flag3);
			if(flag3>=10)
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
//		} catch (Exception e) {
//			objutil.takeScreenShot(result1);
//			e.printStackTrace();
//		}
		
//		try {
//			boolean flag4 = objhomepage.hcpsite();
//			System.out.println(flag4);
//			boolean exp_val=Boolean.parseBoolean(Assert_val);
//			Assert.assertEquals(flag4, exp_val);
//		} catch (Exception e) {
//			objutil.takeScreenShot(result1);
//			e.printStackTrace();
//		}
//		
//		try {
//			boolean flag5=objhomepage.hcpcookiesContainer();
//			boolean exp_val=Boolean.parseBoolean(Assert_val);
//			Assert.assertEquals(flag5, exp_val);
//		} catch (Exception e) {
//			objutil.takeScreenShot(result1);
//			e.printStackTrace();
//		}
//		
//		try {
//			boolean flag6=objhomepage.uiCCPApopup();
//			boolean exp_val=Boolean.parseBoolean(Assert_val);
//			Assert.assertEquals(flag6, exp_val);
//		} catch (Exception e) {
//			objutil.takeScreenShot(result1);
//			e.printStackTrace();
//		}
//		
//		try {
//			Integer flag7=objhomepage.saveCookiesPopup();
//			if(flag7>=20)
//			{
//				Assert.assertTrue(true);
//			}
//			else
//			{
//				Assert.assertTrue(false);
//			}
//		} catch (Exception e) {
////			objutil.takeScreenShot(result1);
//			e.printStackTrace();
//		}
			test.log(LogStatus.PASS, "Verifications PASSED for the specified URL");
		} catch (Exception e) {
//			objutil.takeScreenShot(result1);
			test.log(LogStatus.FAIL, "Verifications FAILED for the specified URL");
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void teardown(ITestResult result,ITestContext context){

//		if(result1.getMethod().getDataProviderMethod()!=null)
//			{
				result.setTestName(context.getAttribute("customTestName").toString());
//			}
//			else {
//				System.out.println("No data provider for this method");
//			}
			objutil.takeScreenShot(result);
			driver.close();
			driver.quit();;
	}
	
	@AfterClass
	public static void endTest()
	{
	report.endTest(test);
	report.flush();
	}
	
	@AfterSuite
	public void teardown()
	{
//		driver.close();
//		driver.quit();
	}
	

}
