package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.user.UserAddressesPageUI;

public class UserAddressesPageObject extends BasePage {
	
	private WebDriver driver;

	public UserAddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isAddressesHeaderDisplayed() {
		return isElementDisplayed(driver, UserAddressesPageUI.ADDRESSES_HEADER);
	}
	
}
