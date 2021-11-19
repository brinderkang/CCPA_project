package ccpa.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import io.github.bonigarcia.wdm.WebDriverManager;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class HomepageTestDPP extends Baseclass {
	
	Homepage2 objhomepage;
	Util objutil;
	static Properties prop;
	WebDriver driver;
	public static ITestResult result;
	static ExtentTest test;
	static ExtentReports report;
	public String imagename;
	
	public HomepageTestDPP()
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
		imagename=URL;
		context.setAttribute("customTestName", URL);
		
		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\rahul.chadha\\Desktop\\BrowserDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
//		WebDriverManager.firefoxdriver().setup();
//		driver=new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		
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

		
		if(URL.equalsIgnoreCase("https://www.her2treatment.com"))
		{
			Thread.sleep(4000);
		}
		
			boolean flag1=objhomepage.cookiesContainer();
			boolean exp_val=Boolean.parseBoolean(Assert_val);
			Assert.assertEquals(flag1, exp_val);
			test.log(LogStatus.PASS, "Cookies container present for specified URL");

			boolean flag2=objhomepage.uiCCPApopup();

			Assert.assertEquals(flag2, exp_val);
			test.log(LogStatus.PASS, "Header and required text is displaying for the specified URL");

			Integer flag3=objhomepage.saveCookiesPopup();
			System.out.println(flag3);
			if(flag3>=10)
			{
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Cokies saved in the browser for the specified URL");
			}
			else
			{
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Cokies  not saved in the browser for the specified URL");
			}

			test.log(LogStatus.PASS, "All Verifications PASSED for the specified URL");
		} catch (Exception e) {
//			objutil.takeScreenShot(result);
			objutil.takeScreenShotextent(imagename);
			test.log(LogStatus.FAIL, "Verifications FAILED for the specified URL");
//			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void teardown(ITestResult result,ITestContext context){


				result.setTestName(context.getAttribute("customTestName").toString());
			
			if(ITestResult.FAILURE== result.getStatus())
			{
				test.log(LogStatus.FAIL, "Verifications FAILED for the specified URL");
				objutil.takeScreenShotextent(imagename);
//				objutil.takeScreenShot(result);
			}
			
			
//			driver.close();
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

