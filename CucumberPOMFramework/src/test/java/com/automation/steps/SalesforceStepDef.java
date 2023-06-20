package com.automation.steps;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.automation.pages.home.HomePage;
import com.automation.pages.login.LoginPage;
import com.automation.utilities.Log4JUtility;
import com.automation.utilities.PropertyUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceStepDef {
	protected static Logger log;
	public static WebDriver driver;
	protected static Log4JUtility logObject = Log4JUtility.getInstance();
	LoginPage login;
	HomePage home;

	public void launchBrowser(String browserName) {
		
		switch (browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().browserVersion("109.0.1").setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();

			break;
		}
		System.out.println(browserName + " browser opened");
	}

	public void goToUrl(String url) {
		driver.get(url);
		log.info(url + "is entered");
	}

	public void closeBrowser() {
		driver.close();
		log.info("current browser closed");
	}

	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log = logObject.getLogger();
	}

	@Before
	public void setUpEachScenario() {
		launchBrowser("chrome");
	}

	@After
	public void tearDown() {
		closeBrowser();
	}

	@Given("user open salesforce application")
	public void user_open_salesforce_application() {
		PropertyUtility pro = new PropertyUtility();
		Properties appProp = pro.loadFile("applicationDataPropertiesSF");
		String url = appProp.getProperty("url");
		goToUrl(url);
	}

	@When("user on {string}")
	public void user_on(String page) {
		if (page.equalsIgnoreCase("loginpage"))
			login = new LoginPage(driver);
		else if (page.equalsIgnoreCase("homepage"))
			home = new HomePage(driver);
	}

	@When("user enters value into text box username as {string}")
	public void user_enters_value_into_text_box_username_as(String userId) {
		login.enterUserName(userId);
	}

	@When("user enters value into text box password as {string}")
	public void user_enters_value_into_text_box_password_as(String password) {
		login.enterPassword(password);
	}

	@When("click on Login button")
	public void click_on_login_button() {
		login.clickLogin();
	}

	@When("verify we can see {string}")
	public void verify_we_can_see(String string) {
		String actualTitle = null;
		String expectedTitle = null;

		if (string.equalsIgnoreCase("loginpage")) {
			actualTitle = login.getTitle();
			expectedTitle = LoginPage.loginPageTitle;
		}

		else if (string.equalsIgnoreCase("homepage")) {
			actualTitle = home.getTitle();
			expectedTitle = HomePage.homePageTitle;
		}

		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Then("user logout")
	public void user_logout() {
		home.userDropDown();
		home.logout();
	}
	
	@Then("verify error Please enter a password")
	public void verify_error_please_enter_a_password() {
	   Assert.assertTrue(login.errorMessage().equals("Please enter your password."));
	}
	
	@When("click remenberMe checkbox")
	public void click_remenber_me_checkbox() {
	   login.checkRememberMe();
	}

	
}
