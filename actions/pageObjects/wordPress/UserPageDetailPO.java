package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.PageGeneratorManager;
import pageUIs.wordPress.UserPageDetailPUI;

public class UserPageDetailPO extends BasePage {
	WebDriver driver;
	
	public UserPageDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCommentTextareaDisplayed() {
		return isElementDisplayed(driver, UserPageDetailPUI.COMMENT_TEXTAREA);
	}

	public boolean isCommentTextareaUndisplayed() {
		return isElementUndisplayed(driver, UserPageDetailPUI.COMMENT_TEXTAREA);
	}

	public boolean isPageTitleDisplayed(String pageTitle) {
		return isElementDisplayed(driver, UserPageDetailPUI.PAGE_TITLE, pageTitle);
	}

	public boolean isPageImageDisplayed(String pageTitle, String uploadedImageName) {
		return isElementDisplayed(driver, UserPageDetailPUI.PAGE_IMAGE, pageTitle, uploadedImageName);
	}

	public boolean isPageBodyDisplayed(String pageBody) {
		return isElementDisplayed(driver, UserPageDetailPUI.PAGE_BODY, pageBody);
	}
	
	public UserHomePO clickOnHomePageLink() {
		clickOnElement(driver, UserPageDetailPUI.HOME_PAGE_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
}
