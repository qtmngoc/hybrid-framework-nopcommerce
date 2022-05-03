package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.DownloadableProductsPageUI;

public class DownloadableProductsPageObject extends BasePage {
	private WebDriver driver;

	public DownloadableProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDownloadableProductsPageDisplayed() {
		waitElementVisible(driver, DownloadableProductsPageUI.DOWNLOADABLE_PRODUCTS_HEADER);
		return isElementDisplayed(driver, DownloadableProductsPageUI.DOWNLOADABLE_PRODUCTS_HEADER);
	}
}
