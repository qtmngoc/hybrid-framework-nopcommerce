package pageObjects.nopCommerce.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.user.UserSearchPageUI;

public class UserSearchPageObject extends BasePage {
	
	private WebDriver driver;

	public UserSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isSearchHeaderDisplayed() {
		return isElementDisplayed(driver, UserSearchPageUI.SEARCH_HEADER);
	}

	public void inputIntoSearchTextbox(String productName) {
		sendKeysToElement(driver, UserSearchPageUI.SEARCH_TEXTBOX, productName);
	}

	public void clickOnSearchButton() {
		clickOnElement(driver, UserSearchPageUI.SEARCH_BUTTON);
	}
	
	public String getWarningMessage() {
		return getElementText(driver, UserSearchPageUI.WARNING_MESSAGE);
	}

	public String getNoResultMessage() {
		return getElementText(driver, UserSearchPageUI.NO_RESULT_MESSAGE);
	}

	public List<WebElement> getRelativeProductNameResults() {
		return getListElements(driver, UserSearchPageUI.PRODUCT_NAME);
	}

	public WebElement getAbsoluteProductNameResult() {
		return getElement(driver, UserSearchPageUI.PRODUCT_NAME);
	}

}
