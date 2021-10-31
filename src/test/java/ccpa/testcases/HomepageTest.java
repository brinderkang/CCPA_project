package ccpa.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import ccpa.base.Baseclass;
import ccpa.pages.Homepage;
import ccpa.utilities.Util;

public class HomepageTest extends Baseclass {
	
	Homepage objhomepage;
	Util objutil;
	static Properties prop;
	
	public HomepageTest()
	{
		super();
	}
	
	@BeforeSuite
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
		String newurl=prop.getProperty("url1");
		initialisation(newurl);
		objhomepage = new Homepage();
		objutil = new Util();
		
	}
	
	
	@Test(priority=1)
	public void CCPA_TC_1_Verify_Page_Title()
	{
		String title=objhomepage.Title();
		Assert.assertEquals(title, "RA, GCA, SJIA, PJIA Treatment | ACTEMRA® (tocilizumab)");
	}
	
	@Test(priority=2)
	public void CCPA_TC_2_Verify_Display_Logo()
	{
		boolean flag=objhomepage.logo();
		Assert.assertEquals(flag, true);
	}
	@Test(priority=3)
	public void CCPA_TC_3_Verify_CCPA_is_triggered_for_patient_domain()
	{
		boolean flag=objhomepage.cookiesContainer();
		Assert.assertEquals(flag, true);
	}
	
	
	@Test(priority=4)
	public void CCPA_TC_4_UI_of_CCPA_pop_up_for_patient_domain() throws InterruptedException
	{
		boolean flag=objhomepage.uiCCPApopup();
		Assert.assertEquals(flag, true);
	}
	@Test(priority=5)
	public void CCPA_TC_5_Verify_Cookies_Saved_on_Accept_Button_Click_For_patient_domain() throws InterruptedException
	{
		Integer flag=objhomepage.saveCookiesPopup();
		if(flag>=36)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
//		Assert.assertEquals(flag, "37");
	}
//	**********************************
//	Tests For HCP Site
	@Test(priority=6)
	public void CCPA_TC_6_Verify_CCPA_is_triggered_for_HCPsite() throws InterruptedException
	{
		boolean flag = objhomepage.hcpsite();
		Assert.assertEquals(flag, true);
	}
	@Test(priority=7)
	public void CCPA_TC_7_Verify_CCPA_is_triggered_for_HCPsite() throws InterruptedException
	{
		boolean flag=objhomepage.hcpcookiesContainer();
		Assert.assertEquals(flag, true);
	}
	@Test(priority=8)
	public void CCPA_TC_8_Verify_UI_of_CCPA_pop_up_for_HCPsite() throws InterruptedException
	{
		boolean flag=objhomepage.uiCCPApopup();
		Assert.assertEquals(flag, true);
	}
	@Test(priority=9)
	public void CCPA_TC_9_Verify_Cookies_Saved_on_Accept_Button_Click_For_HCPSite() throws InterruptedException
	{
		Integer flag=objhomepage.saveCookiesPopup();
		if(flag>=36)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
//		Assert.assertEquals(flag, "37");
	}
	
	
	@AfterMethod
	public void teardown(ITestResult result1){

		objutil.takeScreenShot(result1);
	}
	
	@AfterSuite
	public void teardown()
	{
		driver.close();
		driver.quit();
	}
	

}
