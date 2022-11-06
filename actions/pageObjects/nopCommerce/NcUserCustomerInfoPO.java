package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.NcUserCustomerInfoPUI;

public class NcUserCustomerInfoPO extends NcBasePage {
	
	private WebDriver driver;

	public NcUserCustomerInfoPO(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Verify Customer Info page is displayed")
	public boolean isCustomerInfoHeaderDisplayed() {
		return isElementDisplayed(driver, NcUserCustomerInfoPUI.CUSTOMER_INFO_HEADER);
	}
	
	public void inputIntoCompanyNameTextbox(String companyNameValue) {
		sendKeysToElement(driver, NcUserCustomerInfoPUI.COMPANY_NAME_TEXTBOX, companyNameValue);
	}

	public void clickOnNewsletterCheckbox() {
		checkCheckboxOrRadio(driver, NcUserCustomerInfoPUI.NEWSLETTER_CHECKBOX);
	}

	public void clickOnSaveButton() {
		clickOnElement(driver, NcUserCustomerInfoPUI.SAVE_BUTTON);
	}

	public String getCompanyName() {
		return getElementAttribute(driver, NcUserCustomerInfoPUI.COMPANY_NAME_TEXTBOX, "value");
	}
	
	public boolean isNewsletterCheckboxSelected() {
		return isElementSelected(driver, NcUserCustomerInfoPUI.NEWSLETTER_CHECKBOX);
	}
	
}
