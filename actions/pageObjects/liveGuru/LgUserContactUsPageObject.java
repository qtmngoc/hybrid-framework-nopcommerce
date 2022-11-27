package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import pageObjects.liveGuru.navigation.LgUserFooterContainerPageObject;
import pageUIs.liveGuru.LgUserContactUsPageUI;

public class LgUserContactUsPageObject extends LgUserFooterContainerPageObject {
	
	private WebDriver driver;

	public LgUserContactUsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isContactUsHeaderDisplayed() {
		waitForElementVisible(driver, LgUserContactUsPageUI.CONTACT_US_HEADER);
		return isElementDisplayed(driver, LgUserContactUsPageUI.CONTACT_US_HEADER);
	}
	
}
