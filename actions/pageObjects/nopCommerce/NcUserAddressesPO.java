package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import pageUIs.nopCommerce.NcUserAddressesPUI;

public class NcUserAddressesPO extends NcBasePage {
	
	private WebDriver driver;

	public NcUserAddressesPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isAddressesHeaderDisplayed() {
		return isElementDisplayed(driver, NcUserAddressesPUI.ADDRESSES_HEADER);
	}
	
}
