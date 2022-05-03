package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.OrdersPageUI;

public class OrdersPageObject extends BasePage {
	private WebDriver driver;

	public OrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOrdersPageDisplayed() {
		waitElementVisible(driver, OrdersPageUI.ORDERS_HEADER);
		return isElementDisplayed(driver, OrdersPageUI.ORDERS_HEADER);
	}
}
