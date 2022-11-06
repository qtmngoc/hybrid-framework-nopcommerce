package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.LgBasePage;
import commons.liveGuru.LgPageGeneratorManager;
import pageUIs.liveGuru.LgAdminLoginPageUI;

public class LgAdminLoginPageObject extends LgBasePage{

	private WebDriver driver;

	public LgAdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoUserNameTextbox(String adminUserName) {
		waitForElementVisible(driver, LgAdminLoginPageUI.USER_NAME_TEXTBOX);
		sendKeysToElement(driver, LgAdminLoginPageUI.USER_NAME_TEXTBOX, adminUserName);
	}

	public void inputIntoPasswordTextbox(String adminPassword) {
		waitForElementVisible(driver, LgAdminLoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LgAdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
	}

	public LgAdminCustomersPageObject clickOnLoginButton() {
		waitForElementClickable(driver, LgAdminLoginPageUI.LOGIN_BUTTON);
		clickOnElement(driver, LgAdminLoginPageUI.LOGIN_BUTTON);
		return LgPageGeneratorManager.getAdminCustomersPage(driver);
	}

}
