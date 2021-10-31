package ccpa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ccpa.utilities.Util;

public class Baseclass {
	
	public static WebDriver driver;
	static Properties prop;
	
	public Baseclass()
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
	
	public void initialisation(String url)
	{
		String browsername=prop.getProperty("browser");
		if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\rahul.chadha\\Desktop\\BrowserDrivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		if(browsername.equals("Firefox"))
		{
		System.setProperty("webdriver.geko.driver", "C:\\Users\\rahul.chadha\\Desktop\\BrowserDrivers\\gekodriver.exe");
		driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(url);
	}
	

}
