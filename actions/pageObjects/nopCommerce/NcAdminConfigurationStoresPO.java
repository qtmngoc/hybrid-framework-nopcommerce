package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import pageUIs.nopCommerce.NcAdminConfigurationStoresPUI;

public class NcAdminConfigurationStoresPO extends NcBasePage {
	
	private WebDriver driver;

	public NcAdminConfigurationStoresPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isStoresHeaderDisplayed() {
		return isElementDisplayed(driver, NcAdminConfigurationStoresPUI.STORES_HEADER);
	}

}
