package com.automation.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.BasePage;

public class LoginPage extends BasePage {

	WebDriver driver;
	public static final String loginPageTitle = "Login | Salesforce";
	
	@FindBy(id="username") WebElement usernameElem;
	@FindBy(id="password") WebElement passwordElem;
	@FindBy(id="Login") WebElement loginElem;
	@FindBy(xpath="//input[@name='rememberUn']") WebElement rememberMeElem;
	@FindBy(xpath="//a[@id='forgot_password_link']") WebElement forgotPassword;
	@FindBy(id="error") WebElement errorMessage;

	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String username) {
		if(username.equals("userId")) {
			enterText(usernameElem, userId, "UserName");
		}
		else if(username.equals("invalidUserId")) {
			enterText(usernameElem, invalidUserId, "UserName");
		}
	}
	
	public void enterPassword(String password) {
		enterText(passwordElem, super.password, "Password");
	}
	
	public String errorMessage() {
		return errorMessage.getText();
	}
	
	public void checkRememberMe() {
		if (!rememberMeElem.isSelected()) {
			rememberMeElem.click();
		}
	}
	
	public void clickLogin() {
		clickElement(loginElem, "Login");
	}
	
	public void forgotPassword() {
		clickElement(forgotPassword,"forgotPass");
	}
	
	public String getTitle() {
		waitUntilPageLoads();
		return getPageTitle();
	}
	
	
}
