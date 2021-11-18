package ccpa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ccpa.base.Baseclass;
import ccpa.utilities.Util;

public class Homepage2 {
	
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
//	@FindBy(xpath="(//*[text()='privacy policy.' or text()='Privacy Policy.'])[2]")
	WebElement privacyPolicyLink;
	@FindBy(xpath="//*[@id='onetrust-pc-btn-handler']")
	WebElement cookiesPreferences;
	@FindBy(xpath="//*[@id='onetrust-accept-btn-handler']")
	WebElement acceptButton;
	@FindBy(xpath="(//span[text()='Healthcare Professionals Site'])[1]")
	WebElement hcpsite;
	@FindBy(xpath="body/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")
	WebElement hcpPopup;
	@FindBy(xpath="//h3[text()='Link to Healthcare Professionals Site']")
	WebElement hcpPopupHeader;
	@FindBy(xpath="(//a[@role='button'])[3]")
	WebElement hcpPopupOKBtn;
	private WebDriver driver;
	
	public Homepage2(WebDriver driver)
	{
		this.driver=driver;
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
	
	public boolean uiCCPApopup() throws InterruptedException
	{
		String expectedText="This website may use certain types of cookies and other technologies to personalize content and to show more personalized ads. By clicking “Accept”, you understand that you are directing Genentech to disclose data that may be considered personal information to third parties for these purposes. For more details on what information we collect, why we collect it and to whom we disclose it, please visit our privacy policy.";
		ouruseofcookies.isDisplayed();
		ccpaText.isDisplayed();
		privacyPolicyLink.isDisplayed();
		cookiesPreferences.isDisplayed();
		String actualText= ccpaText.getText();
//		System.out.println(actualText);
		Thread.sleep(1000);
		if(expectedText.equals(actualText))
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}
	
	public Integer saveCookiesPopup() throws InterruptedException
	{
		Thread.sleep(1000);
		acceptButton.click();
		
		Thread.sleep(1000);
		return Util.getCookiesinfo();
	}
	
	public boolean hcpsite() throws InterruptedException
	{
		if(hcpsite.isDisplayed())
		{
			hcpsite.click();
//			hcpPopup.isDisplayed();
			hcpPopupHeader.isDisplayed();
			Thread.sleep(2000);
			hcpPopupOKBtn.click();
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean hcpcookiesContainer() throws InterruptedException
	{
		Thread.sleep(2000);
//		Util.getCookiesinfo();
		ccpaContainer.isDisplayed();
		return true;
	}
	

}
