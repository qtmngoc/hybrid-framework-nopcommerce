package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import pageUIs.nopCommerce.NcUserMyProductReviewsPUI;

public class NcUserMyProductReviewsPO extends NcBasePage {
	
	private WebDriver driver;

	public NcUserMyProductReviewsPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMyProductReviewsHeaderDisplayed() {
		return isElementDisplayed(driver, NcUserMyProductReviewsPUI.MY_PRODUCT_REVIEWS_HEADER);
	}
	
}
