package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	
	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isCustomerInfoHeaderDisplayed() {
		return isElementDisplayed(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
	}
	
	public void inputIntoCompanyNameTextbox(String companyNameValue) {
		sendKeysToElement(driver, UserCustomerInfoPageUI.COMPANY_NAME_TEXTBOX, companyNameValue);
	}

	public void clickOnNewsletterCheckbox() {
		checkCheckboxRadio(driver, UserCustomerInfoPageUI.NEWSLETTER_CHECKBOX);
	}

	public void clickOnSaveButton() {
		clickOnElement(driver, UserCustomerInfoPageUI.SAVE_BUTTON);
	}

	public String getCompanyName() {
		return getElementAttribute(driver, UserCustomerInfoPageUI.COMPANY_NAME_TEXTBOX, "value");
	}
	
	public boolean isNewsletterCheckboxSelected() {
		return isElementSelected(driver, UserCustomerInfoPageUI.NEWSLETTER_CHECKBOX);
	}
	
}
