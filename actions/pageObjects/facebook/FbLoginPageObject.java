package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.facebook.FbBasePage;
import pageUIs.facebook.FbLoginPageUI;

public class FbLoginPageObject extends FbBasePage {

	WebDriver driver;
	
	public FbLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnCreateNewAccountButton() {
		clickOnElement(driver, FbLoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
	}

	public boolean isEmailTextboxDisplayed() {
		return isElementDisplayed(driver, FbLoginPageUI.EMAIL_TEXTBOX);
	}

	public boolean isConfirmEmailTextboxUndisplayed() {
		return isElementUndisplayed(driver, FbLoginPageUI.CONFIRM_EMAIL_TEXTBOX);
	}

	public void inputIntoEmailTextbox(String email) {
		sendKeysToElement(driver, FbLoginPageUI.EMAIL_TEXTBOX, email);
	}

	public boolean isConfirmEmailTextboxDisplayed() {
		return isElementDisplayed(driver, FbLoginPageUI.CONFIRM_EMAIL_TEXTBOX);
	}

	public void clickCloseIconOnRegisterForm() {
		clickOnElement(driver, FbLoginPageUI.CLOSE_ICON);
	}
	
}
