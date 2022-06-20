package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import commons.nopCommerce.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {

	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Enter {0} into First Name text box")
	public void inputIntoFirstNameTextbox(String firstNameValue) {
		sendKeysToElement(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX, firstNameValue);
	}

	public void inputIntoLastNameTextbox(String lastNameValue) {
		sendKeysToElement(driver, UserRegisterPageUI.LASTNAME_TEXTBOX, lastNameValue);
	}

	public void inputIntoEmailTextbox(String emailValue) {
		sendKeysToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputIntoPasswordTextbox(String passwordValue) {
		sendKeysToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, passwordValue);
	}

	public void inputIntoConfirmPasswordTextbox(String confirmPasswordValue) {
		sendKeysToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPasswordValue);
	}

	@Step("Click on Register button")
	public void clickOnRegisterButton() {
		clickOnElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMessageBelowFirstNameTextbox() {
		return getElementText(driver, UserRegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessageBelowLastNameTextbox() {
		return getElementText(driver, UserRegisterPageUI.LASTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessageBelowEmailTextbox() {
		return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageBelowPasswordTextbox() {
		return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageBelowConfirmPasswordTextbox() {
		return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	@Step("Verify registration success message is displayed")
	public String getRegisterSuccessMessage() {
		return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getErrorMessageExistingEmail() {
		return getElementText(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}
	
	public UserHomePageObject clickOnLogoutLink() {
		clickOnElement(driver, UserRegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
}
