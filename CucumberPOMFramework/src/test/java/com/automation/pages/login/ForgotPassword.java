package com.automation.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class ForgotPassword extends BasePage {
	
	WebDriver driver;
	public static final String forgotPasswordTitle = "Check Your Email | Salesforce";
	
	@FindBy(xpath="//input[@id='un']") WebElement usernameElem;
	@FindBy(xpath="//input[@id='continue']") WebElement buttonElem;
	
	public ForgotPassword(WebDriver driver) {
		super(driver);
	}
	public void enterUserName(String username) {
		enterText(usernameElem, username, "UserName");
	}
	public void continueButton() {
		clickElement(buttonElem, "Button" );
	}

}
