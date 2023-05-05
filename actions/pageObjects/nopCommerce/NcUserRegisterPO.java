package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import commons.nopCommerce.NcPageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.NcUserRegisterPUI;

public class NcUserRegisterPO extends NcBasePage {

	private WebDriver driver;

	public NcUserRegisterPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Enter '{0}' into First Name text box")
	public void inputIntoFirstNameTextbox(String firstName) {
		sendKeysToElement(driver, NcUserRegisterPUI.FIRSTNAME_TEXTBOX, firstName);
	}

	@Step("Enter '{0}' into Last Name text box")
	public void inputIntoLastNameTextbox(String lastName) {
		sendKeysToElement(driver, NcUserRegisterPUI.LASTNAME_TEXTBOX, lastName);
	}

	@Step("Enter '{0}' into Email text box")
	public void inputIntoEmailTextbox(String email) {
		sendKeysToElement(driver, NcUserRegisterPUI.EMAIL_TEXTBOX, email);
	}

	@Step("Enter '{0}' into Password text box")
	public void inputIntoPasswordTextbox(String password) {
		sendKeysToElement(driver, NcUserRegisterPUI.PASSWORD_TEXTBOX, password);
	}

	@Step("Enter '{0}' into Confirm Password text box")
	public void inputIntoConfirmPasswordTextbox(String confirmPassword) {
		sendKeysToElement(driver, NcUserRegisterPUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}

	@Step("Click on Register button")
	public void clickOnRegisterButton() {
		clickOnElement(driver, NcUserRegisterPUI.REGISTER_BUTTON);
	}

	public String getErrorMessageBelowFirstNameTextbox() {
		return getElementText(driver, NcUserRegisterPUI.FIRSTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessageBelowLastNameTextbox() {
		return getElementText(driver, NcUserRegisterPUI.LASTNAME_ERROR_MESSAGE);
	}

	public String getErrorMessageBelowEmailTextbox() {
		return getElementText(driver, NcUserRegisterPUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageBelowPasswordTextbox() {
		return getElementText(driver, NcUserRegisterPUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageBelowConfirmPasswordTextbox() {
		return getElementText(driver, NcUserRegisterPUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	@Step("Verify registration success message is displayed")
	public String getRegisterSuccessMessage() {
		return getElementText(driver, NcUserRegisterPUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getErrorMessageExistingEmail() {
		return getElementText(driver, NcUserRegisterPUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}
	
	/*public NcUserHomePO clickOnLogoutLink() {
		clickOnElement(driver, NcUserRegisterPUI.LOGOUT_LINK);
		return NcPageGeneratorManager.getUserHomePage(driver);
	}*/
	
	public NcUserLoginPO clickOnLoginLink() {
		clickOnElement(driver, NcUserRegisterPUI.LOGIN_LINK);
		return NcPageGeneratorManager.getUserLoginPage(driver);
	}
	
}
