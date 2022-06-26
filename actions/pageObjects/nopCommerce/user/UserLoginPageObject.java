package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import commons.nopCommerce.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Enter '{0}' into Email text box")
	public void inputIntoEmailTextbox(String email) {
		sendKeysToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, email);
	}

	@Step("Enter '{0}' into Password text box")
	public void inputIntoPasswordTextbox(String password) {
		sendKeysToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
	}

	@Step("Click on Login button")
	public UserHomePageObject clickOnLoginButton() {
		clickOnElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public String getErrorMessageBelowEmailTextbox() {
		return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getLoginUnsuccessMessage() {
		return getElementText(driver, UserLoginPageUI.LOGIN_UNSUCCESS_MESSAGE);
	}

	public UserHomePageObject loginAsUser(String emailValue, String passwordValue) {
		inputIntoEmailTextbox(emailValue);
		inputIntoPasswordTextbox(passwordValue);
		return clickOnLoginButton();
	}
	
}
