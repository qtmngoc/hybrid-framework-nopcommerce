package pageObjects.nopCommerce;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.nopCommerce.NcBasePage;
import pageUIs.nopCommerce.NcUserSearchPUI;

public class NcUserSearchPO extends NcBasePage {
	
	private WebDriver driver;

	public NcUserSearchPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isSearchHeaderDisplayed() {
		return isElementDisplayed(driver, NcUserSearchPUI.SEARCH_HEADER);
	}

	public void inputIntoSearchTextbox(String productName) {
		sendKeysToElement(driver, NcUserSearchPUI.SEARCH_TEXTBOX, productName);
	}

	public void clickOnSearchButton() {
		clickOnElement(driver, NcUserSearchPUI.SEARCH_BUTTON);
	}
	
	public String getWarningMessage() {
		return getElementText(driver, NcUserSearchPUI.WARNING_MESSAGE);
	}

	public String getNoResultMessage() {
		return getElementText(driver, NcUserSearchPUI.NO_RESULT_MESSAGE);
	}

	public List<WebElement> getRelativeProductNameResults() {
		return getListElements(driver, NcUserSearchPUI.PRODUCT_NAME);
	}

	public WebElement getAbsoluteProductNameResult() {
		return getElement(driver, NcUserSearchPUI.PRODUCT_NAME);
	}

}
