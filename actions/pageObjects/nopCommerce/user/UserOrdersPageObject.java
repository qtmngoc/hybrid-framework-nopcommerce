package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.user.UserOrdersPageUI;

public class UserOrdersPageObject extends BasePage {
	
	private WebDriver driver;

	public UserOrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOrdersHeaderDisplayed() {
		return isElementDisplayed(driver, UserOrdersPageUI.ORDERS_HEADER);
	}
	
}
