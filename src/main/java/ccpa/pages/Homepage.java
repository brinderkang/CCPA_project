package ccpa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ccpa.base.Baseclass;
import ccpa.utilities.Util;

public class Homepage extends Baseclass {
	
//	Page Factory
	@FindBy(xpath="//img[@src='/content/dam/gene/actemra/logos/logo-actemra.genecoreimg.750.png']")
	WebElement logo;
	@FindBy(xpath="//div[@class='ot-sdk-container']")
	WebElement ccpaContainer;
	@FindBy(xpath="//*[@id='onetrust-policy-title']")
	WebElement ouruseofcookies;
	@FindBy(xpath="//*[@id='onetrust-policy-text']")
	WebElement ccpaText;
	@FindBy(xpath="//*[text()='privacy policy.']")
	WebElement privacyPolicyLink;
	@FindBy(xpath="//*[@id='onetrust-pc-btn-handler']")
	WebElement cookiesPreferences;
	@FindBy(xpath="//*[@id='onetrust-accept-btn-handler']")
	WebElement acceptButton;
	@FindBy(xpath="(//span[text()='Healthcare Professionals Site'])[1]")
	WebElement hcpsite;
	
	public Homepage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String Title()

	{
		return driver.getTitle();
	}
	public boolean logo()
	{
		return logo.isDisplayed();
	}
	
	public boolean cookiesContainer()
	{
		Util.getCookiesinfo();
		ccpaContainer.isDisplayed();
		return true;
	}
	
	public boolean uiCCPApopup()
	{
		ouruseofcookies.isDisplayed();
		ccpaText.isDisplayed();
		privacyPolicyLink.isDisplayed();
		cookiesPreferences.isDisplayed();
		return true;
	}
	
	public boolean saveCookiesPopup()
	{
		acceptButton.click();
		Util.getCookiesinfo();
		return true;
	}
	
	public boolean hcpsite()
	{
		if(hcpsite.isDisplayed())
		{
			hcpsite.click();
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
