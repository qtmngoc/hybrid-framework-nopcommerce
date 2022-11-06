package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import commons.nopCommerce.NcPageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.NcUserLoginPUI;

public class NcUserLoginPO extends NcBasePage {
	
	private WebDriver driver;

	public NcUserLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Enter '{0}' into Email text box")
	public void inputIntoEmailTextbox(String email) {
		sendKeysToElement(driver, NcUserLoginPUI.EMAIL_TEXTBOX, email);
	}

	@Step("Enter '{0}' into Password text box")
	public void inputIntoPasswordTextbox(String password) {
		sendKeysToElement(driver, NcUserLoginPUI.PASSWORD_TEXTBOX, password);
	}

	@Step("Click on Login button")
	public NcUserHomePO clickOnLoginButton() {
		clickOnElement(driver, NcUserLoginPUI.LOGIN_BUTTON);
		return NcPageGeneratorManager.getUserHomePage(driver);
	}

	public String getErrorMessageBelowEmailTextbox() {
		return getElementText(driver, NcUserLoginPUI.EMAIL_ERROR_MESSAGE);
	}

	public String getLoginUnsuccessMessage() {
		return getElementText(driver, NcUserLoginPUI.LOGIN_UNSUCCESS_MESSAGE);
	}

	public NcUserHomePO loginAsUser(String emailValue, String passwordValue) {
		inputIntoEmailTextbox(emailValue);
		inputIntoPasswordTextbox(passwordValue);
		return clickOnLoginButton();
	}
	
}
