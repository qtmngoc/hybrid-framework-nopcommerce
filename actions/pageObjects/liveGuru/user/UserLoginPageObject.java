package pageObjects.liveGuru.user;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.BasePage;
import commons.liveGuru.PageGeneratorManager;
import pageUIs.liveGuru.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject clickOnCreateButton() {
		waitElementClickable(driver, UserLoginPageUI.CREATE_BUTTON);
		clickOnElement(driver, UserLoginPageUI.CREATE_BUTTON);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	public void inputIntoEmailTextbox(String emailValue) {
		waitElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputIntoPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, passwordValue);
	}

	public UserMyDashboardPageObject clickOnLoginButton() {
		waitElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickOnElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserMyDashboardPage(driver);
	}
	
	public String getLoginUnsuccessMessage() {
		waitElementVisible(driver, UserLoginPageUI.LOGIN_UNSUCCESS_MESSAGE);
		return getElementText(driver, UserLoginPageUI.LOGIN_UNSUCCESS_MESSAGE);
	}
	
}
