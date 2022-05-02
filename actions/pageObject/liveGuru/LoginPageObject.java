package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveGuru.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickOnCreateButton() {
		waitElementClickable(driver, LoginPageUI.CREATE_BUTTON);
		clickOnElement(driver, LoginPageUI.CREATE_BUTTON);
		return new RegisterPageObject(driver);
	}

	public void inputIntoEmailTextbox(String emailValue) {
		waitElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputIntoPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordValue);
	}

	public MyDashboardPageObject clickOnLoginButton() {
		waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickOnElement(driver, LoginPageUI.LOGIN_BUTTON);
		return new MyDashboardPageObject(driver);
	}
}
