package functionalComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import siteFactory.SiteFactory;

@SuppressWarnings({"unused"})
public class CommonFunctions 
{

	public static Properties properties = new Properties();
	public static String propertyFilePath = System.getProperty("user.home")+"/eclipse-workspace/RealEats_Automation/src/test/resources/PropertyFiles/config.properties";
	public static WebDriver driver;
	private SiteFactory factory;
		
	public CommonFunctions() {
		this.factory = new SiteFactory();
	}

	public static void openURL(String url) throws IOException 
	{
		driver.get(url);
	}

	public void initiateBrowser() throws IOException 
	{
		InputStream input = new FileInputStream(propertyFilePath);
		properties.load(input);
		String browserName = properties.getProperty("browserName");

		if(browserName.equalsIgnoreCase("IE")) 
		{

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			System.out.println("Starting Web Browser.........");

			openURL(properties.getProperty("url"));

		} 

		else if(browserName.equalsIgnoreCase("Firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			System.out.println("Starting Web Browser.........");

			openURL(properties.getProperty("url"));
		} 

		else if(browserName.equalsIgnoreCase("Chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("Starting Web Browser.........");

			openURL(properties.getProperty("url"));	

		}
	}

	public void switchToDefaultContent(){
		driver.switchTo().defaultContent();
	}

}
