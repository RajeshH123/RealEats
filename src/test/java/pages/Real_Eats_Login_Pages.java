package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import siteFactory.SiteFactory;

public class Real_Eats_Login_Pages extends Real_Eats_BasePage
{ 
	public Real_Eats_Login_Pages(SiteFactory factory,WebDriver driver) 
	{
		super(factory,driver);
	}
	
	//Log In Link
	protected By logIn_LnkObj = By.xpath("//a[@class='link-desktop btn-hover mr-3' and text()='Log In']");
	protected By email_Obj = By.id("email");
	protected By password_Obj = By.id("password");
	protected By logIn_BtnObj = By.xpath("//button[contains(text(),'Log in')]");
	
	public void clickOnLoginLink()
	{
		unconditionalWait(2);
		findElementAndClick(logIn_LnkObj);
	}
	
	public void enterEmail(String EmailID)
	{
		unconditionalWait(2);
		findElementAndSendkey(email_Obj, EmailID);
	}
	
	public void enterPassword(String Password)
	{
		unconditionalWait(2);
		findElementAndSendkey(password_Obj, Password);
	}
	
	public void clickOnLoginButton()
	{
		unconditionalWait(2);
		findElementAndClick(logIn_BtnObj);
	}

}
