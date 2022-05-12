package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import commons.nopCommerce.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
	
	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public AdminDashboardPageObject loginAsAdmin(String emailValue, String passwordValue) {
		sendKeysToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailValue);
		sendKeysToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, passwordValue);
		clickOnElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}

}
