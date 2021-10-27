package ccpa.testcases;

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
	
	public HomepageTest()
	{
		super();
	}
	
	@BeforeSuite
	public void setUp()
	{
		initialisation();
		objhomepage = new Homepage();
		objutil = new Util();
		
	}
	
	
	@Test(priority=1)
	public void verifyTitle()
	{
		String title=objhomepage.Title();
		Assert.assertEquals(title, "RA, GCA, SJIA, PJIA Treatment | ACTEMRA® (tocilizumab)");
	}
	
	@Test(priority=2)
	public void verifyLogo()
	{
		boolean flag=objhomepage.logo();
		Assert.assertEquals(flag, true);
	}
	@Test(priority=3)
	public void CCPA_TC_1_Verify_CCPA_is_triggered_for_patient_domain()
	{
		boolean flag=objhomepage.cookiesContainer();
		Assert.assertEquals(flag, true);
	}
	@Test(priority=4)
	public void CCPA_TC_2_Verify_CCPA_is_triggered_for_HCPsite()
	{
		boolean flag = objhomepage.hcpsite();
		Assert.assertEquals(flag, true);
	}
	
	@Test(priority=5)
	public void CCPA_TC_3_UI_of_CCPA_pop_up_for_patient_domain()
	{
		boolean flag=objhomepage.uiCCPApopup();
		Assert.assertEquals(flag, true);
	}
	@Test(priority=6)
	public void CCPA_TC_4_Verify_that_clicking_on_Accept_button_Cookie_should_saved_in_system()
	{
		boolean flag=objhomepage.saveCookiesPopup();
		Assert.assertEquals(flag, true);
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
