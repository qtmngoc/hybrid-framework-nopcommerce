package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.user.UserDownloadableProductsPageUI;

public class UserDownloadableProductsPageObject extends BasePage {
	
	private WebDriver driver;

	public UserDownloadableProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDownloadableProductsHeaderDisplayed() {
		return isElementDisplayed(driver, UserDownloadableProductsPageUI.DOWNLOADABLE_PRODUCTS_HEADER);
	}
	
}
