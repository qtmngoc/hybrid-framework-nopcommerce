package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.NcBasePage;
import pageUIs.nopCommerce.NcUserOrdersPUI;

public class NcUserOrdersPO extends NcBasePage {
	
	private WebDriver driver;

	public NcUserOrdersPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOrdersHeaderDisplayed() {
		return isElementDisplayed(driver, NcUserOrdersPUI.ORDERS_HEADER);
	}
	
}
