package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import pageObjects.liveGuru.navigation.LgUserFooterContainerPageObject;
import pageUIs.liveGuru.LgUserPrivacyPolicyPageUI;

public class LgUserPrivacyPolicyPageObject extends LgUserFooterContainerPageObject {
	
	private WebDriver driver;

	public LgUserPrivacyPolicyPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isPrivacyPolicyHeaderDisplayed() {
		waitForElementVisible(driver, LgUserPrivacyPolicyPageUI.PRIVACY_POLICY_HEADER);
		return isElementDisplayed(driver, LgUserPrivacyPolicyPageUI.PRIVACY_POLICY_HEADER);
	}
	
}
