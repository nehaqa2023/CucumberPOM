package com.automation.pages.base;

import java.time.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utilities.Log4JUtility;
import com.automation.utilities.PropertyUtility;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Log4JUtility logObject = Log4JUtility.getInstance();
	protected Logger log;
	//protected ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	
	public String userId;
	public String password;
	public String invalidUserId;
	public String invalidPassword;

	public void propUtil() {
		PropertyUtility pro = new PropertyUtility();
		Properties appProp = pro.loadFile("applicationDataPropertiesSF");

		userId = appProp.getProperty("login.valid.userid");
		password = appProp.getProperty("login.valid.password");
		invalidUserId = appProp.getProperty("login.invalid.userid");
		invalidPassword = appProp.getProperty("login.invalid.password");
	}


	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
		log = logObject.getLogger();
		propUtil();
	}

	public void actionSendKeys(String key) {
		Actions actions = new Actions(driver);
		actions.sendKeys(key).build().perform();
		log.info(key + " is entered.");
	}

	public void enterText(WebElement element, String data, String objectName) {
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			log.info(objectName + " is entered to the " + objectName + " field");
			//report.logTestInfo(objectName + " is entered to the " + objectName + " field");
		} else {
			log.error("userNameElement is not displayed");
		}

	}

	public void enterText(String id, String data, String objectName) {
		By elemId = By.id(id);
		WebElement element = driver.findElement(elemId);

		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(data);
			log.info(objectName + " is entered to the " + objectName + " field");
			//report.logTestInfo(objectName + " is entered to the " + objectName + " field");
		} else {
			log.error("userNameElement is not displayed");
		}

	}

	public void clickElement(WebElement element, String objectName) {
		waitUntilElementVisible(element, "Waiting..");
		if (element.isDisplayed()) {
			element.click();
			log.info("Pass: " + objectName + " element clicked");
			//report.logTestInfo(objectName + " element clicked");
		} else {
			log.error("fail:" + objectName + " element not displayed");
		}
	}

	public void clickElement(String id, String objectName) {
		waitImplicitly(2000);

		By login = By.id(id);
		WebElement element = driver.findElement(login);

		if (element.isDisplayed()) {
			element.click();
			log.info("Pass: " + objectName + " element clicked");
			//report.logTestInfo(objectName + " element clicked");
		} else {
			log.error("fail:" + objectName + " element not displayed");
		}
	}

	public void clickElementByXpath(String xpath, String objectName) {

		waitImplicitly(2000);

		By elem = By.xpath(xpath);
		WebElement element = driver.findElement(elem);

		if (element.isDisplayed()) {
			element.click();
			log.info("Pass: " + objectName + " element clicked");
			//report.logTestInfo(objectName + " element clicked");
		} else {
			log.error("fail:" + objectName + " element not displayed");
		}
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public String getTextFromWebElement(WebElement element, String name) {
		if (element.isDisplayed()) {
			return element.getText();
		} else {
			log.info(name + "web element is not displayed");
			return null;
		}
	}

	public Alert switchToAlert() {

		Alert alert = driver.switchTo().alert();
		log.info("switched to alert");
		return alert;

	}

	public void acceptAlert(Alert alert) {

		log.info("Alert accepted");
		alert.accept();
	}

	public String getalertText(Alert alert) {
		log.info("extracting text in the alert");
		return alert.getText();
	}

	public void dismissAlert() {

		Alert alert = switchToAlert();
		alert.dismiss();
		log.info("Alert dismissed");
	}

	public void switchToFrame(WebElement frame, String frameName) {
		waitUntilPageLoads();
		driver.switchTo().frame(frame);
		log.info("Switch to frame: " + frameName);
	}

	public void waitUntilElementVisible(WebElement ele, String ObjName) {
		log.info("waiting for a web element");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	// waits

	public void waitImplicitly(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void waitUntilPageLoads() {
		log.info("waiting until page loads with 30 sec maximum");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	public void WaitUntilElementIsVisible(WebElement ele, String objName) {
		log.info("waiting for an web element" + objName + "for its visibility");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void WaitUntilPresenceOfElementLocatedBy(By locator, String objName) {
		log.info("waiting for an web element" + objName + "for its visibility");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitUntilAlertIsPresent() {
		log.info("waiting for alert to be present");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitUntilElementToBeClickable(By locator, String objName) {
		log.info("waiting for an web element" + objName + "to be clickable");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitFluentForVisibility(WebElement ele, String objName) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void selectByTextData(WebElement element, String text, String objName) {
		Select selectElem = new Select(element);
		selectElem.selectByVisibleText(text);
		log.info(objName + " selected " + text);
	}

	public void selectByIndexData(WebElement element, int index, String objName) {
		Select selectElem = new Select(element);
		selectElem.selectByIndex(index);
		log.info(objName + " selected ");
	}

	public void selectByValueData(WebElement element, String text, String objName) {
		Select selectElem = new Select(element);
		selectElem.selectByValue(text);
		log.info(objName + " selected ");
	}

	public void switchToWindowOpened(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
		log.info("switched to new window");
	}

	public List<String> getListText(WebElement webElem) {
		// WebElement targetDropdown = driver.findElement(By.id("order-same"));
		Select target = new Select(webElem);
		List<WebElement> targetListElements = target.getOptions();
		List<String> targetList = new ArrayList<String>();
		for (WebElement webElement : targetListElements) {
			targetList.add(webElement.getText());
		}
		Collections.sort(targetList);
		return targetList;
	}

	public List<String> getMenuItems(List<WebElement> elems) {

		List<String> actualList = new ArrayList<String>(elems.size());

		for (int i = 0; i < elems.size(); i++) {
			actualList.add(elems.get(i).getText());
		}

		if (actualList != null) {
			Collections.sort(actualList);
		}

		return actualList;
	}

	public List<String> getExpectedMenuItems() {
		List<String> expectedList = new ArrayList<String>();
		expectedList.add("My Profile");
		expectedList.add("My Settings");
		expectedList.add("Developer Console");
		expectedList.add("Switch to Lightning Experience");
		expectedList.add("Logout");
		Collections.sort(expectedList);
		return expectedList;
	}

	public void waitUntilPageTitleContains(String title) {
		log.info("waiting for title " + title + " to be displayed");
		// WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains(title));
	}

	public boolean isReportInSelectedTab(WebElement webElem) {
		Select selectedTab = new Select(webElem);
		List<WebElement> elements = selectedTab.getOptions();
		
		for (WebElement wElem : elements) {
			if (wElem.getText().equals("Reports")) {
				return true;
			}
		}
		return false;
	}
}
