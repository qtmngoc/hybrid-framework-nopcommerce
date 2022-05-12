package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.user.UserMyProductReviewsPageUI;

public class UserMyProductReviewsPageObject extends BasePage {
	
	private WebDriver driver;

	public UserMyProductReviewsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMyProductReviewsHeaderDisplayed() {
		return isElementDisplayed(driver, UserMyProductReviewsPageUI.MY_PRODUCT_REVIEWS_HEADER);
	}
	
}
