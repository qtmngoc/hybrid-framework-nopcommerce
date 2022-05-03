package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.AddressesPageUI;

public class AddressesPageObject extends BasePage {
	private WebDriver driver;

	public AddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isAddressesPageDisplayed() {
		waitElementVisible(driver, AddressesPageUI.ADDRESSES_HEADER);
		return isElementDisplayed(driver, AddressesPageUI.ADDRESSES_HEADER);
	}
}
