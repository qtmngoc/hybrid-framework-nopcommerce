package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import pageObjects.liveGuru.navigation.LgUserSidebarMyAccountPageObject;
import pageUIs.liveGuru.LgUserRecurringProfilesPageUI;

public class LgUserRecurringProfilesPageObject extends LgUserSidebarMyAccountPageObject {
	
	private WebDriver driver;

	public LgUserRecurringProfilesPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isRecurringProfilesHeaderDisplayed() {
		waitForElementVisible(driver, LgUserRecurringProfilesPageUI.RECURRING_PROFILES_HEADER);
		return isElementDisplayed(driver, LgUserRecurringProfilesPageUI.RECURRING_PROFILES_HEADER);
	}
	
}
