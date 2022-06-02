package pageObjects.liveGuru.user;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.BasePage;
import commons.liveGuru.PageGeneratorManager;
import pageUIs.liveGuru.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoFirstNameTextbox(String firstNameValue) {
		waitElementVisible(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX, firstNameValue);
	}

	public void inputIntoLastNameTextbox(String lastNameValue) {
		waitElementVisible(driver, UserRegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.LASTNAME_TEXTBOX, lastNameValue);
	}

	public void inputIntoEmailTextbox(String emailValue) {
		waitElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputIntoPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, passwordValue);
	}

	public void inputIntoConfirmPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, passwordValue);
	}

	public UserMyDashboardPageObject clickOnRegisterButton() {
		waitElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickOnElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getUserMyDashboardPage(driver);
	}
	
}
