package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import functionalComponent.CommonFunctions;
import siteFactory.SiteFactory;

@SuppressWarnings("unused")
public class Real_Eats_BasePage extends CommonFunctions 
{

	SiteFactory factory;


	static protected int Timeout = Integer.valueOf(properties.getProperty("Timeout"));

	public Real_Eats_BasePage(SiteFactory factory, WebDriver driver) {
		this.factory = factory;

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, Timeout), this);
		driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
	}

	public void findElementAndClick(By locator) 
	{

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		Wait<WebDriver> wait = new WebDriverWait(driver, 10);		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(element);
		element.click();
		driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);

	}

	public void findElementAndClick(WebElement element) {

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		Wait<WebDriver> wait = new WebDriverWait(driver, 10);
		if (element.isEnabled() && element.isDisplayed()) 
		{
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
			highlightElement(element);
			element.click();
			driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
		}

	}


	public void findElementClickAndSendkey(By locator, String locatorValue) {


		if( waitUntilThrobberInvisible() )
		{

			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

			Wait<WebDriver> wait = new FluentWait<WebDriver>(CommonFunctions.driver)
					.withTimeout(Duration.ofSeconds(8))
					.pollingEvery( Duration.ofSeconds(1) )
					.ignoring(NoSuchElementException.class);
			WebElement element = getActiveWebElement(locator);
			element.click();

			Actions action = new Actions(driver);

			highlightElement(element);

			action.moveToElement(element);
			action.click();
			action.keyDown(Keys.CONTROL);
			action.sendKeys("a");
			action.keyUp(Keys.CONTROL);
			action.sendKeys(Keys.DELETE);
			action.sendKeys(locatorValue);
			action.build().perform();
			driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
		}

	}

	protected void waitUntilObjVisible(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) 
		{
			Assert.fail(locator.toString() + " is NOT visible");
		}
	}

	public void findElementAndSendkey(By locator, String elementValue) {
		waitUntilObjVisible(locator);
		highlightElement(locator);
		driver.findElement(locator).sendKeys(elementValue);
		unhighlightLast(locator);
	}

	public void clearElementAndSendkey(By locator, String elementValue) {
		waitUntilObjVisible(locator);
		//highlightElement(locator);
		driver.findElement(locator).clear();
		waitUntilObjVisible(locator);
		driver.findElement(locator).sendKeys(elementValue);

	}

	public boolean waitUntilThrobberInvisible() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		Wait<WebDriver> wait = new FluentWait<WebDriver>(CommonFunctions.driver)
				.withTimeout(Duration.ofSeconds(20))
				.pollingEvery( Duration.ofSeconds(1) )
				.ignoring(NoSuchElementException.class);

		boolean invisibilityOfElementLocated = false;
		By xpathThrobber = By.id("pega_ui_load");

		try {
			driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);

			return invisibilityOfElementLocated;

		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
			Assert.fail(xpathThrobber.toString() + " is still visible");
			return false;
		}

	}

	public void waitForElementPresent(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}

	public void waitForThePageToReLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(CommonFunctions.driver, 10);
		wait.until(pageLoadCondition);
	} 

	public static void unconditionalWait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			Assert.fail("Thread Interrupted");
			e.printStackTrace();
		}
	}

	// *******************************>END BaseUIPage - Wait Methods<********************************//

	// ****************************>BaseUIPage - Keyboard Action Methods<****************************//

	public void tabKeyboard() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB);
		act.pause(java.time.Duration.ofMillis(500));
		act.build().perform();
	}

	public WebElement getActiveWebElement(By locator) {

		if( waitUntilThrobberInvisible() )
		{
			List<WebElement> elementList = driver.findElements(locator);

			for (WebElement currElement : elementList) 
			{
				if (currElement.isEnabled() && currElement.isDisplayed()) 
				{
					return currElement;
				}
			}
		}

		return null;

	}

	public void scrollToElementBy(By locator) {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator) );
			WebElement ele = getActiveWebElement(locator);
			highlightElement(ele);
			JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", ele);

		}catch (Exception e) 
		{
			System.err.println(e);
		}

	}


	public void switchToWindowHandle() 
	{

		unconditionalWait(4);
		String newWindowID = null;

		Set<String> allWindowIDS = driver.getWindowHandles();

		for (Iterator<String> i = allWindowIDS.iterator(); i.hasNext();) 
		{
			newWindowID = i.next();
		}
		driver.switchTo().window(newWindowID);
		driver.manage().window().maximize();

	}

	public String getCurrentWindowHandle() {
		String activeWindowID = null;
		try{
			if( waitUntilThrobberInvisible() )
			{
				activeWindowID = driver.getWindowHandle();
			}
		}
		catch (Exception e) {
		}
		return activeWindowID;
	}

	public void switchToWindowHandle(String subWindowHandler) {

		unconditionalWait(2);
		try{
			String activeWindowID = driver.getWindowHandle();
			if( waitUntilThrobberInvisible() )
			{
				if( !activeWindowID.equals( subWindowHandler ) )
				{
					driver.switchTo().window(subWindowHandler);
				}
			}
		}
		catch (Exception e) {
		}

	}

	protected boolean isTextPresent(String text){
		try{
			boolean b = driver.getPageSource().contains(text);
			return b;
		}
		catch(Exception e){
			return false;
		}
	}

	public static boolean isDisplayed(By locator) {
		try {
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			//WebElement element = driver.findElement(locator);

			if(driver.findElement(locator).isDisplayed()){
				driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
				return true;
			}
		}catch (NoSuchElementException ex) {
			driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
			return false;
		}
		driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
		return false;
	}



	public static boolean isExisting(By locator) {

		boolean isExisting = false;
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		List<WebElement> listOfWebElements= driver.findElements(locator);

		if(null != listOfWebElements && listOfWebElements.size()>0) {
			isExisting = true;
		}
		driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
		return isExisting;			

	}


	public static boolean isEnabled(By locator) {
		try {
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			WebElement element = driver.findElement(locator);

			if( element.isEnabled() )
			{
				driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
				return element.isEnabled();
			}
		}catch (NoSuchElementException ex) {
			driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
			return false;
		}
		driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
		return false;
	}

	public boolean isByLocatorVisible(By locator) {
		Boolean isPresent = true;
		try {			
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

			Wait<WebDriver> wait = new FluentWait<WebDriver>(CommonFunctions.driver)
					.withTimeout(Duration.ofSeconds(36))
					.pollingEvery(Duration.ofSeconds(2))
					.ignoring(NoSuchElementException.class);

			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		} catch (Exception e) {
			isPresent = false;
			driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);			
		}

		driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
		return isPresent;
	} 

	private void highlightElement(By locator){
		WebElement element= driver.findElement(locator);

		JavascriptExecutor javascriptExecutor  = ((JavascriptExecutor) driver);
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "background-color: yellow; color: red; border: 2px solid red;");
	}

	private void unhighlightLast(By locator) {
		try {
			WebElement element= driver.findElement(locator);
			// if there already is a highlighted element, unhighlight it
			JavascriptExecutor javascriptExecutor  = ((JavascriptExecutor) driver);
			javascriptExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		} catch (StaleElementReferenceException ignored) {
			// the page got reloaded, the element isn't there
		} catch (NoSuchElementException ignored) {
			// the page got reloaded, the element isn't there
		}

	}

	private void highlightElement(WebElement element){

		JavascriptExecutor javascriptExecutor  = ((JavascriptExecutor) driver);
		javascriptExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "background-color: yellow; color: red; border: 2px solid red;");
	}

	public void switchToIFrame(String iframeID) {
		driver.switchTo().frame(iframeID);
	}

}
