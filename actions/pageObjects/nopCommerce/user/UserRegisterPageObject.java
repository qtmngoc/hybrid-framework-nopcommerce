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

	@Step("Enter '{0}' into First Name text box")
	public void inputIntoFirstNameTextbox(String firstName) {
		sendKeysToElement(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	@Step("Enter '{0}' into Last Name text box")
	public void inputIntoLastNameTextbox(String lastName) {
		sendKeysToElement(driver, UserRegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}

	@Step("Enter '{0}' into Email text box")
	public void inputIntoEmailTextbox(String email) {
		sendKeysToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, email);
	}

	@Step("Enter '{0}' into Password text box")
	public void inputIntoPasswordTextbox(String password) {
		sendKeysToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	@Step("Enter '{0}' into Confirm Password text box")
	public void inputIntoConfirmPasswordTextbox(String confirmPassword) {
		sendKeysToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
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
