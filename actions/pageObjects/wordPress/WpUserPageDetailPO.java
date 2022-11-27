package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpPageGeneratorManager;
import pageUIs.wordPress.WpUserPageDetailPUI;

public class WpUserPageDetailPO extends WpBasePage {
	WebDriver driver;
	
	public WpUserPageDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPageCommentTextareaDisplayed() {
		return isElementDisplayed(driver, WpUserPageDetailPUI.COMMENT_TEXTAREA);
	}

	public boolean isPageCommentTextareaUndisplayed() {
		return isElementUndisplayed(driver, WpUserPageDetailPUI.COMMENT_TEXTAREA);
	}

	public boolean isPageTitleDisplayed(String pageTitle) {
		return isElementDisplayed(driver, WpUserPageDetailPUI.PAGE_TITLE, pageTitle);
	}

	public boolean isPageImageDisplayed(String pageTitle, String uploadedImageName) {
		return isElementDisplayed(driver, WpUserPageDetailPUI.PAGE_IMAGE, pageTitle, uploadedImageName);
	}

	public boolean isPageBodyDisplayed(String pageBody) {
		return isElementDisplayed(driver, WpUserPageDetailPUI.PAGE_BODY, pageBody);
	}
	
	public WpUserHomePO clickOnHomePageLink() {
		clickOnElement(driver, WpUserPageDetailPUI.HOME_PAGE_LINK);
		return WpPageGeneratorManager.getUserHomePage(driver);
	}
	
}
