package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import pageUIs.nopCommerce.NcUserDownloadableProductsPUI;

public class NcUserDownloadableProductsPO extends NcBasePage {
	
	private WebDriver driver;

	public NcUserDownloadableProductsPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDownloadableProductsHeaderDisplayed() {
		return isElementDisplayed(driver, NcUserDownloadableProductsPUI.DOWNLOADABLE_PRODUCTS_HEADER);
	}
	
}
