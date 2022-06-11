package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.facebook.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {

	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnCreateNewAccountButton() {
		clickOnElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
	}

	public boolean isEmailTextboxDisplayed() {
		return isElementDisplayed(driver, LoginPageUI.EMAIL_TEXTBOX);
	}

	public boolean isConfirmEmailTextboxUndisplayed() {
		return isElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_TEXTBOX);
	}

	public void inputIntoEmailTextbox(String email) {
		sendKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}

	public boolean isConfirmEmailTextboxDisplayed() {
		return isElementDisplayed(driver, LoginPageUI.CONFIRM_EMAIL_TEXTBOX);
	}

	public void clickCloseIconOnRegisterForm() {
		clickOnElement(driver, LoginPageUI.CLOSE_ICON);
	}
	
}
