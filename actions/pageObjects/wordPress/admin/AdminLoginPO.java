package pageObjects.wordPress.admin;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;

public class AdminLoginPO extends BasePage {
	
	WebDriver driver;
	
	public AdminLoginPO(WebDriver driver) {
		this.driver = driver;
	}
}
