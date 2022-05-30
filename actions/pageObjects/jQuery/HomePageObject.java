package pageObjects.jQuery;

import org.openqa.selenium.WebDriver;

import commons.jQuery.BasePage;
import pageUIs.jQuery.HomePageUI;

public class HomePageObject extends BasePage{
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPaginationByPageNumber(String pageNumber) {
		clickOnElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}

	public boolean isPageNumberActived(String pageNumber) {
		return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVATED_BY_NUMBER, pageNumber);
	}

}
