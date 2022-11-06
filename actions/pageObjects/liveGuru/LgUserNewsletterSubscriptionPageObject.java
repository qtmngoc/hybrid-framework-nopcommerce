package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import pageObjects.liveGuru.navigation.LgUserSidebarMyAccountPageObject;
import pageUIs.liveGuru.LgUserNewsletterSubscriptionPageUI;

public class LgUserNewsletterSubscriptionPageObject extends LgUserSidebarMyAccountPageObject {
	
	private WebDriver driver;

	public LgUserNewsletterSubscriptionPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isNewsletterSubscriptionHeaderDisplayed() {
		waitForElementVisible(driver, LgUserNewsletterSubscriptionPageUI.NEWSLETTER_SUBSCRIPTION_HEADER);
		return isElementDisplayed(driver, LgUserNewsletterSubscriptionPageUI.NEWSLETTER_SUBSCRIPTION_HEADER);
	}
	
}
