package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import pageObjects.liveGuru.navigation.LgUserFooterContainerPageObject;
import pageUIs.liveGuru.LgUserSearchTermsPageUI;

public class LgUserSearchTermsPageObject extends LgUserFooterContainerPageObject {
	
	private WebDriver driver;

	public LgUserSearchTermsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isSearchTermsHeaderDisplayed() {
		waitForElementVisible(driver, LgUserSearchTermsPageUI.SEARCH_TERMS_HEADER);
		return isElementDisplayed(driver, LgUserSearchTermsPageUI.SEARCH_TERMS_HEADER);
	}
	
}
