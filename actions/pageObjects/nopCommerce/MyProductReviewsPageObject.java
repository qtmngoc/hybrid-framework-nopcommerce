package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.MyProductReviewsPageUI;

public class MyProductReviewsPageObject extends BasePage {
	private WebDriver driver;

	public MyProductReviewsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMyProductReviewsPageDisplayed() {
		waitElementVisible(driver, MyProductReviewsPageUI.MY_PRODUCT_REVIEWS_HEADER);
		return isElementDisplayed(driver, MyProductReviewsPageUI.MY_PRODUCT_REVIEWS_HEADER);
	}
}
