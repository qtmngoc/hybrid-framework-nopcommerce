package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpPageGeneratorManager;
import pageUIs.wordPress.WpAdminLoginPUI;

public class WpAdminLoginPO extends WpBasePage {	
	WebDriver driver;
	
	public WpAdminLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoUsernameTextbox(String username) {
		sendKeysToElement(driver, WpAdminLoginPUI.USERNAME_TEXTBOX, username);
	}
	
	public void inputIntoPasswordTextbox(String password) {
		sendKeysToElement(driver, WpAdminLoginPUI.PASSWORD_TEXTBOX, password);
	}

	public void clickOnContinueButton() {
		clickOnElement(driver, WpAdminLoginPUI.CONTINUE_BUTTON);
	}
	
	public WpAdminDashboardPO clickOnLoginButton() {
		clickOnElement(driver, WpAdminLoginPUI.LOGIN_BUTTON);
		return WpPageGeneratorManager.getAdminDashboardPage(driver);
	}
	
	public String getLoginErrorMessage() {
		return getElementText(driver, WpAdminLoginPUI.ERROR_MESSAGE);
	}
	
}
