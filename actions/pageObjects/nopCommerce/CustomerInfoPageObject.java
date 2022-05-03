package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public CustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isCustomerInfoPageDisplayed() {
		waitElementVisible(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
	}
	
	public void inputIntoCompanyNameTextbox(String companyNameValue) {
		waitElementVisible(driver, CustomerInfoPageUI.COMPANY_NAME_TEXTBOX);
		sendKeysToElement(driver, CustomerInfoPageUI.COMPANY_NAME_TEXTBOX, companyNameValue);
	}

	public void clickOnNewsletterCheckbox() {
		waitElementClickable(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
		checkCheckboxRadio(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
	}

	public void clickOnSaveButton() {
		waitElementVisible(driver, CustomerInfoPageUI.SAVE_BUTTON);	
		clickOnElement(driver, CustomerInfoPageUI.SAVE_BUTTON);
	}

	public String getCompanyName() {
		waitElementVisible(driver, CustomerInfoPageUI.COMPANY_NAME_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.COMPANY_NAME_TEXTBOX, "value");
	}
	
	public boolean isNewsletterCheckboxSelected() {
		waitElementVisible(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
		return isElementSelected(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
	}
}
