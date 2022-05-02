package pageObject.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoEmailTextbox(String emailValue) {
		waitElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputIntoPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordValue);
	}

	public HomePageObject clickOnLoginButton() {
		waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickOnElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}

	public String getErrorMessageBelowEmailTextbox() {
		waitElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getLoginUnsuccessMessage() {
		waitElementVisible(driver, LoginPageUI.LOGIN_UNSUCCESS_MESSAGE);
		return getElementText(driver, LoginPageUI.LOGIN_UNSUCCESS_MESSAGE);
	}
}
