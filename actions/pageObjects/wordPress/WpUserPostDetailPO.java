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
}
