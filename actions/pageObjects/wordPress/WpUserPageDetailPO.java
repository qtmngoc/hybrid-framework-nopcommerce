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

	public boolean isPageImageDisplayed(String uploadedImageName) {
		return isElementDisplayed(driver, WpUserPageDetailPUI.PAGE_IMAGE, uploadedImageName);
	}

	public boolean isPageBodyDisplayed(String pageBody) {
		return isElementDisplayed(driver, WpUserPageDetailPUI.PAGE_BODY, pageBody);
	}
	
	public WpUserHomePO clickOnHomePageLink() {
		clickOnElement(driver, WpUserPageDetailPUI.HOME_PAGE_LINK);
		return WpPageGeneratorManager.getUserHomePage(driver);
	}
	
	public void clickOnLikeButton() {
		switchToFrameIframe(driver, WpUserPageDetailPUI.LIKE_IFRAME);
		sleepInSecond(1);
		clickOnElementByJS(driver, WpUserPageDetailPUI.LIKE_BUTTON);
		switchToDefaultContent(driver);
	}
	
	public void inputIntoCommentTextarea(String comment) {
		sendKeysToElement(driver, WpUserPageDetailPUI.COMMENT_TEXTAREA, comment);
	}
	
	public void clickOnPostCommentButton() {
		clickOnElement(driver, WpUserPageDetailPUI.POST_COMMENT_BUTTON);
	}

	public String getPageCommentContent() {
		return getElementText(driver, WpUserPageDetailPUI.COMMENT_CONTENT);
	}

	public boolean isYouLikeThisMessageDisplayed() {
		switchToFrameIframe(driver, WpUserPageDetailPUI.LIKE_IFRAME);
		return isElementDisplayed(driver, WpUserPageDetailPUI.YOU_LIKE_THIS_MESSAGE);
	}

	public String getOneThoughtMessage() {
		return getElementText(driver, WpUserPageDetailPUI.ONE_THOUGHT_MESSAGE);
	}

	public String getCommentsClosedMessage() {
		return getElementText(driver, WpUserPageDetailPUI.COMMENTS_CLOSED_MESSAGE);
	}
	
}
