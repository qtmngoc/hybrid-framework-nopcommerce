package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.nopCommerce.BasePage;
import pageUIs.nopCommerce.RewardPointsPageUI;

public class RewardPointsPageObject extends BasePage {
	private WebDriver driver;

	public RewardPointsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRewardPointsPageDisplayed() {
		waitElementVisible(driver, RewardPointsPageUI.REWARD_POINTS_HEADER);
		return isElementDisplayed(driver, RewardPointsPageUI.REWARD_POINTS_HEADER);
	}
}
