package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveGuru.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoFirstNameTextbox(String firstNameValue) {
		waitElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstNameValue);
	}

	public void inputIntoLastNameTextbox(String lastNameValue) {
		waitElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastNameValue);
	}

	public void inputIntoEmailTextbox(String emailValue) {
		waitElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputIntoPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, passwordValue);
	}

	public void inputIntoConfirmPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, passwordValue);
	}

	public MyDashboardPageObject clickOnRegisterButton() {
		waitElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickOnElement(driver, RegisterPageUI.REGISTER_BUTTON);
		return new MyDashboardPageObject(driver);
	}
}
