package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageFactory extends BasePageFactory {
	private WebDriver driver;

	public LoginPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "Email")
	private WebElement emailTextbox;

	@FindBy(id = "Password")
	private WebElement passwordTextbox;

	@FindBy(css = "button.login-button")
	private WebElement loginButton;

	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;

	@CacheLookup
	@FindBy(css = "div.validation-summary-errors")
	private WebElement loginUnsucessMessage;

	public void inputIntoEmailTextbox(String emailValue) {
		waitElementVisible(driver, emailTextbox);
		sendKeysToElement(driver, emailTextbox, emailValue);
	}

	public void inputIntoPasswordTextbox(String passwordValue) {
		waitElementVisible(driver, passwordTextbox);
		sendKeysToElement(driver, passwordTextbox, passwordValue);
	}

	public void clickOnLoginButton() {
		waitElementClickable(driver, loginButton);
		clickOnElement(driver, loginButton);
	}

	public String getErrorMessageBelowEmailTextbox() {
		waitElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getLoginUnsuccessMessage() {
		waitElementVisible(driver, loginUnsucessMessage);
		return getElementText(driver, loginUnsucessMessage);
	}
}
