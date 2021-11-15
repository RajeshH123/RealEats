package actions;

import siteFactory.SiteFactory;

public class Real_Eats_Login_Actions 
{
	SiteFactory factory;
		
	public Real_Eats_Login_Actions(SiteFactory factory) 
	{
		this.factory = factory; 
	}
	
	public void clickOnLoginLink()
	{
		factory.Real_Eats_Login_Pages().clickOnLoginLink();
	}
	
	public void enterEmailID(String EmailID)
	{
		factory.Real_Eats_Login_Pages().enterEmail(EmailID);
	}
	
	public void enterPassword(String Password)
	{
		factory.Real_Eats_Login_Pages().enterPassword(Password);
	}
	
	public void clickOnLoginBtn()
	{
		factory.Real_Eats_Login_Pages().clickOnLoginButton();
	}
	
}