package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.PageGeneratorManager;
import pageUIs.wordPress.AdminLoginPUI;

public class AdminLoginPO extends BasePage {
	
	WebDriver driver;
	
	public AdminLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoUsernameTextbox(String username) {
		sendKeysToElement(driver, AdminLoginPUI.USERNAME_TEXTBOX, username);
	}
	
	public void inputIntoPasswordTextbox(String password) {
		sendKeysToElement(driver, AdminLoginPUI.PASSWORD_TEXTBOX, password);
	}

	public void clickOnContinueButton() {
		clickOnElement(driver, AdminLoginPUI.CONTINUE_BUTTON);
	}
	
	public AdminDashboardPO clickOnLoginButton() {
		clickOnElement(driver, AdminLoginPUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
	
	public String getLoginErrorMessage() {
		return getElementText(driver, AdminLoginPUI.ERROR_MESSAGE);
	}
	
}
