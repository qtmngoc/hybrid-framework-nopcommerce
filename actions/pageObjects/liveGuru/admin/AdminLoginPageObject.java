package pageObjects.liveGuru.admin;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.BasePage;
import commons.liveGuru.PageGeneratorManager;
import pageUIs.liveGuru.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{

	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoUserNameTextbox(String adminUserName) {
		waitElementVisible(driver, AdminLoginPageUI.USER_NAME_TEXTBOX);
		sendKeysToElement(driver, AdminLoginPageUI.USER_NAME_TEXTBOX, adminUserName);
	}

	public void inputIntoPasswordTextbox(String adminPassword) {
		waitElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
	}

	public AdminCustomersPageObject clickOnLoginButton() {
		waitElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickOnElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminCustomersPage(driver);
	}

}
