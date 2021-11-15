package siteFactory;

import functionalComponent.*;
import org.openqa.selenium.WebDriver;
import actions.*;
import pages.*;

public class SiteFactory {

	public WebDriver driverObject() 
	{
		return CommonFunctions.driver;
	}

	public CommonFunctions Commonfunction() 
	{
		return new CommonFunctions();
	}

	public Real_Eats_Login_Actions Real_Eats_Login_Actions() 
	{
		return new Real_Eats_Login_Actions(this);
	}
	
	public Real_Eats_Login_Pages Real_Eats_Login_Pages() 
	{
		return new Real_Eats_Login_Pages(this, driverObject());
	}

}
