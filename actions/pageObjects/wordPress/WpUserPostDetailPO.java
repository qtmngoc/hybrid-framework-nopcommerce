package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import pageUIs.wordPress.WpUserPostDetailPUI;

public class WpUserPostDetailPO extends WpBasePage {
	WebDriver driver;
	
	public WpUserPostDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, WpUserPostDetailPUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostImageDisplayed(String imageUploadedName) {
		return isElementDisplayed(driver, WpUserPostDetailPUI.POST_IMAGE, imageUploadedName);
	}

	public boolean isPostCategoryDisplayed(String postCategory) {
		return isElementDisplayed(driver, WpUserPostDetailPUI.POST_CATEGORY_TEXT, postCategory);
	}

	public boolean isPostPublishedDateDisplayed(String publishedDate) {
		return isElementDisplayed(driver, WpUserPostDetailPUI.PUBLISHED_DATE_TEXT, publishedDate);
	}

	public boolean isPostBodyDisplayed(String postBody) {
		return isElementDisplayed(driver, WpUserPostDetailPUI.POST_BODY_TEXT, postBody);
	}

	public boolean isPostAuthorDisplayed(String authorName) {
		return isElementDisplayed(driver, WpUserPostDetailPUI.POST_AUTHOR_TEXT, authorName);
	}

	public boolean isPostTagDisplayed(String postTag) {
		return isElementDisplayed(driver, WpUserPostDetailPUI.POST_TAG_TEXT, postTag);
	}

	public boolean isPostCommentTextareaDisplayed() {
		return isElementDisplayed(driver, WpUserPostDetailPUI.COMMENT_TEXTAREA);
	}

	public boolean isPostCommentTextareaUndisplayed() {
		return isElementUndisplayed(driver, WpUserPostDetailPUI.COMMENT_TEXTAREA);
	}
	
	public void clickOnLikeButton() {
		switchToFrameIframe(driver, WpUserPostDetailPUI.LIKE_IFRAME);
		sleepInSecond(1);
		clickOnElementByJS(driver, WpUserPostDetailPUI.LIKE_BUTTON);
		switchToDefaultContent(driver);
	}
	
	public void inputIntoCommentTextarea(String comment) {
		sendKeysToElement(driver, WpUserPostDetailPUI.COMMENT_TEXTAREA, comment);
	}
	
	public void clickOnPostCommentButton() {
		clickOnElement(driver, WpUserPostDetailPUI.POST_COMMENT_BUTTON);
	}

	public String getPostCommentContent() {
		return getElementText(driver, WpUserPostDetailPUI.COMMENT_CONTENT);
	}

	public boolean isYouLikeThisMessageDisplayed() {
		switchToFrameIframe(driver, WpUserPostDetailPUI.LIKE_IFRAME);
		return isElementDisplayed(driver, WpUserPostDetailPUI.YOU_LIKE_THIS_MESSAGE);
	}

	public String getOneThoughtMessage() {
		return getElementText(driver, WpUserPostDetailPUI.ONE_THOUGHT_MESSAGE);
	}

	public String getCommentsClosedMessage() {
		return getElementText(driver, WpUserPostDetailPUI.COMMENTS_CLOSED_MESSAGE);
	}
	
}

