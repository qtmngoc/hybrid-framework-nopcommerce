package pageFactories.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.nopCommerce.NcBasePageFactory;

public class NcRegisterPageFactory extends NcBasePageFactory {
	private WebDriver driver;

	public NcRegisterPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "FirstName")
	private WebElement firstNameTextbox;

	@FindBy(id = "LastName")
	private WebElement lastNameTextbox;

	@FindBy(id = "Email")
	private WebElement emailTextbox;

	@FindBy(id = "Password")
	private WebElement passwordTextbox;

	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPasswordTextbox;

	@FindBy(id = "register-button")
	private WebElement registerButton;

	@FindBy(id = "FirstName-error")
	private WebElement firstNameErrorMessage;

	@FindBy(id = "LastName-error")
	private WebElement lastNameErrorMessage;

	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;

	@FindBy(id = "Password-error")
	private WebElement passwordErrorMessage;

	@FindBy(id = "ConfirmPassword-error")
	private WebElement confirmPasswordErrorMessage;

	@FindBy(css = "div.message-error li")
	private WebElement existingEmailErrorMessage;

	@FindBy(className = "result")
	private WebElement registerSuccessMessage;

	@FindBy(className = "ico-logout")
	private WebElement logoutLink;

	public void inputIntoFirstNameTextbox(String firstNameValue) {
		waitElementVisible(driver, firstNameTextbox);
		sendKeysToElement(driver, firstNameTextbox, firstNameValue);
	}

	public void inputIntoLastNameTextbox(String lastNameValue) {
		waitElementVisible(driver, lastNameTextbox);
		sendKeysToElement(driver, lastNameTextbox, lastNameValue);
	}

	public void inputIntoEmailTextbox(String emailValue) {
		waitElementVisible(driver, emailTextbox);
		sendKeysToElement(driver, emailTextbox, emailValue);
	}

	public void inputIntoPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, passwordTextbox);
		sendKeysToElement(driver, passwordTextbox, passwordValue);
	}

	public void inputIntoConfirmPasswordTextbox(String confirmPasswordValue) {
		waitElementVisible(driver, confirmPasswordTextbox);
		sendKeysToElement(driver, confirmPasswordTextbox, confirmPasswordValue);
	}

	public void clickOnRegisterButton() {
		waitElementClickable(driver, registerButton);
		clickOnElement(driver, registerButton);
	}

	public void clickOnLogoutLink() {
		waitElementClickable(driver, logoutLink);
		clickOnElement(driver, logoutLink);
	}

	public String getErrorMessageBelowFirstNameTextbox() {
		waitElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getErrorMessageBelowLastNameTextbox() {
		waitElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	public String getErrorMessageBelowEmailTextbox() {
		waitElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getErrorMessageBelowPasswordTextbox() {
		waitElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getErrorMessageBelowConfirmPasswordTextbox() {
		waitElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);
	}

	public String getRegisterSuccessMessage() {
		waitElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}

	public String getErrorMessageExistingEmail() {
		waitElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}
}
