package pageObject.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.MyAccountPageUI;

public class MyAccountPageObject extends BasePage {
	private WebDriver driver;

	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputIntoCompanyNameTextbox(String companyNameValue) {
		waitElementVisible(driver, MyAccountPageUI.COMPANY_NAME_TEXTBOX);
		sendKeysToElement(driver, MyAccountPageUI.COMPANY_NAME_TEXTBOX, companyNameValue);
	}

	public void clickOnNewsletterCheckbox() {
		waitElementClickable(driver, MyAccountPageUI.NEWSLETTER_CHECKBOX);
		checkCheckboxRadio(driver, MyAccountPageUI.NEWSLETTER_CHECKBOX);
	}

	public void clickOnSaveButton() {
		waitElementVisible(driver, MyAccountPageUI.SAVE_BUTTON);	
		clickOnElement(driver, MyAccountPageUI.SAVE_BUTTON);
	}

	public String getCompanyName() {
		waitElementVisible(driver, MyAccountPageUI.COMPANY_NAME_TEXTBOX);
		return getElementAttribute(driver, MyAccountPageUI.COMPANY_NAME_TEXTBOX, "value");
	}
	
	public boolean isNewsletterCheckboxSelected() {
		waitElementVisible(driver, MyAccountPageUI.NEWSLETTER_CHECKBOX);
		return isElementSelected(driver, MyAccountPageUI.NEWSLETTER_CHECKBOX);
	}
}
