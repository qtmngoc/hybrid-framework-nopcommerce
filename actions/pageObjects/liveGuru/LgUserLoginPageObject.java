package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.LgBasePage;
import commons.liveGuru.LgPageGeneratorManager;
import pageUIs.liveGuru.LgUserLoginPageUI;

public class LgUserLoginPageObject extends LgBasePage {
	
	private WebDriver driver;

	public LgUserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public LgUserRegisterPageObject clickOnCreateButton() {
		waitForElementClickable(driver, LgUserLoginPageUI.CREATE_BUTTON);
		clickOnElement(driver, LgUserLoginPageUI.CREATE_BUTTON);
		return LgPageGeneratorManager.getUserRegisterPage(driver);
	}

	public void inputIntoEmailTextbox(String emailValue) {
		waitForElementVisible(driver, LgUserLoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LgUserLoginPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputIntoPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, LgUserLoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LgUserLoginPageUI.PASSWORD_TEXTBOX, passwordValue);
	}

	public LgUserMyAccountDashboardPageObject clickOnLoginButton() {
		waitForElementClickable(driver, LgUserLoginPageUI.LOGIN_BUTTON);
		clickOnElement(driver, LgUserLoginPageUI.LOGIN_BUTTON);
		return LgPageGeneratorManager.getUserMyAccountDashboardPage(driver);
	}
	
	public String getLoginUnsuccessMessage() {
		waitForElementVisible(driver, LgUserLoginPageUI.LOGIN_UNSUCCESS_MESSAGE);
		return getElementText(driver, LgUserLoginPageUI.LOGIN_UNSUCCESS_MESSAGE);
	}
	
}
