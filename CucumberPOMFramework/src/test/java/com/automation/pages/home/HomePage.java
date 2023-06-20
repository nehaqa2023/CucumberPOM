package com.automation.pages.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class HomePage extends BasePage {
	
	WebDriver driver;
	public static final String homePageTitle = "Home Page ~ Salesforce - Developer Edition";
	

	@FindBy(xpath="//*[@id='userNav']") WebElement userDropDownElem;
    @FindBy(xpath="//a[@title='Logout']") WebElement logoutElem;
    @FindBy(xpath="//a[@title='My Profile']") WebElement myProfileElem;
    @FindBy(xpath="//a[@title='My Settings']") WebElement mySettingsElem;
    @FindBy(xpath="//a[@title='Developer Console (New Window)']") WebElement developerConsoleElem;
    @FindBy(xpath="//a[@title='Accounts Tab']") WebElement accountsElem;
    @FindBy(xpath="//div[@id='userNav-menuItems']/a") List<WebElement> userMenuItems;
    
    public HomePage(WebDriver driver) {
		super(driver);
	}
    
    public void userDropDown() {
    	clickElement(userDropDownElem, "User DropDown");
    }
    
    public void logout() {
    	clickElement(logoutElem, "Logout" );
    }
    
    public void myProfile() {
    	clickElement(myProfileElem,"My Profile");
    }
    
    public void mySettings() {
    	clickElement(mySettingsElem,"My Settings");
    }

	public void clickDeveloperConsole() {
		clickElement(developerConsoleElem, "Developer Console");
	}

	public void closeDeveloperConsole() {
		driver.close();
	}

	public void clickAccount() {
		clickElement(accountsElem, "Accounts");
	}
	
	public List<String> getUserMenuItemsList () {
		return getMenuItems(userMenuItems);
	}
	
	public List<String> getExpectedUserMenuItemsList () {
		return getExpectedMenuItems();
	}
	
	public String getTitle() {
		waitUntilPageLoads();
		return getPageTitle();
	}
   
}
