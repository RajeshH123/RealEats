package stepDefinitions;

import functionalComponent.CommonFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import siteFactory.SiteFactory;

public class Real_Eats_Login_TS 
{
	SiteFactory factory;
			
	public Real_Eats_Login_TS(SiteFactory factory) 
	{
		this.factory = factory;
	}

	@Given("^User opens browzer And enters the URL$")
	public void initiateBrowzer() throws Exception
	{
		factory.Commonfunction().initiateBrowser();
	}
	
	@And("^On Home Page clicks the LogIn Link$")
	public void clickOnLoginLink() throws Exception
	{
		factory.Real_Eats_Login_Actions().clickOnLoginLink();
	}
	
	@Given("^User enters the '(.*)' and '(.*)'$")
	public void enterCredentials(String EmailId, String Password) throws Exception
	{
		factory.Real_Eats_Login_Actions().enterEmailID(EmailId);
		factory.Real_Eats_Login_Actions().enterPassword(Password);		
	}
	
	@And("^Click on Login button$")
	public void clickOnLoginButton() throws Exception
	{
		factory.Real_Eats_Login_Actions().clickOnLoginBtn();
	}
	
}
