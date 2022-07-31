package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import pageUIs.wordPress.UserPostDetailPUI;

public class UserPostDetailPO extends BasePage {
	WebDriver driver;
	
	public UserPostDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, UserPostDetailPUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostImageDisplayed(String imageUploadedName) {
		return isElementDisplayed(driver, UserPostDetailPUI.POST_IMAGE, imageUploadedName);
	}

	public boolean isPostCategoryDisplayed(String postCategory) {
		return isElementDisplayed(driver, UserPostDetailPUI.POST_CATEGORY_TEXT, postCategory);
	}

	public boolean isPostPublishedDateDisplayed(String publishedDate) {
		return isElementDisplayed(driver, UserPostDetailPUI.PUBLISHED_DATE_TEXT, publishedDate);
	}

	public boolean isPostBodyDisplayed(String postBody) {
		return isElementDisplayed(driver, UserPostDetailPUI.POST_BODY_TEXT, postBody);
	}

	public boolean isPostAuthorDisplayed(String authorName) {
		return isElementDisplayed(driver, UserPostDetailPUI.POST_AUTHOR_TEXT, authorName);
	}

	public boolean isPostTagDisplayed(String postTag) {
		return isElementDisplayed(driver, UserPostDetailPUI.POST_TAG_TEXT, postTag);
	}

	public boolean isCommentTextareaDisplayed() {
		return isElementDisplayed(driver, UserPostDetailPUI.COMMENT_TEXTAREA);
	}

	public boolean isCommentTextareaUndisplayed() {
		return isElementUndisplayed(driver, UserPostDetailPUI.COMMENT_TEXTAREA);
	}
}
