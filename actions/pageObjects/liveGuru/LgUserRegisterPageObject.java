package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.liveGuru.LgBasePage;
import commons.liveGuru.LgPageGeneratorManager;
import pageUIs.liveGuru.LgUserRegisterPageUI;

public class LgUserRegisterPageObject extends LgBasePage {
	private WebDriver driver;

	public LgUserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputIntoFirstNameTextbox(String firstNameValue) {
		waitForElementVisible(driver, LgUserRegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(driver, LgUserRegisterPageUI.FIRSTNAME_TEXTBOX, firstNameValue);
	}

	public void inputIntoLastNameTextbox(String lastNameValue) {
		waitForElementVisible(driver, LgUserRegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(driver, LgUserRegisterPageUI.LASTNAME_TEXTBOX, lastNameValue);
	}

	public void inputIntoEmailTextbox(String emailValue) {
		waitForElementVisible(driver, LgUserRegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LgUserRegisterPageUI.EMAIL_TEXTBOX, emailValue);
	}

	public void inputIntoPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, LgUserRegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LgUserRegisterPageUI.PASSWORD_TEXTBOX, passwordValue);
	}

	public void inputIntoConfirmPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, LgUserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LgUserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, passwordValue);
	}

	public LgUserMyAccountDashboardPageObject clickOnRegisterButton() {
		waitForElementClickable(driver, LgUserRegisterPageUI.REGISTER_BUTTON);
		clickOnElement(driver, LgUserRegisterPageUI.REGISTER_BUTTON);
		return LgPageGeneratorManager.getUserMyAccountDashboardPage(driver);
	}
	
}
