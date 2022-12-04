package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpPageGeneratorManager;
import pageUIs.wordPress.WpAdminPostAllPUI;

public class WpAdminPostAllPO extends WpBasePage {
	WebDriver driver;

	public WpAdminPostAllPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public WpAdminPostNewPO clickOnAddNewPostButton() {
		clickOnElement(driver, WpAdminPostAllPUI.ADD_NEW_POST_BUTTON);
		return WpPageGeneratorManager.getAdminPostNewPage(driver);
	}

	public WpAdminPostNewPO clickOnPostTitleLink(String postTitle) {
		clickOnElement(driver, WpAdminPostAllPUI.POST_TITLE_LINK, postTitle);	
		return WpPageGeneratorManager.getAdminPostNewPage(driver);
	}
	
	public void clickOnOpenSearchIcon() {
		clickOnElement(driver, WpAdminPostAllPUI.OPEN_SEARCH_ICON);
	}
	
	public void clickOnCloseSearchIcon() {
		clickOnElement(driver, WpAdminPostAllPUI.CLOSE_SEARCH_ICON);
	}

	public void clickOnEllipsisMenu(String postTitle, String menuItem) {
		clickOnElement(driver, WpAdminPostAllPUI.ELLIPSIS_MENU_BY_TITLE, postTitle);
		sleepInSecond(1);
		clickOnElement(driver, WpAdminPostAllPUI.ELLIPSIS_MENU_ITEM, menuItem);
		sleepInSecond(1);
	}

	public void clickOnDeleteEllipsisMenu(String postTitle, String menuItem) {
		clickOnElement(driver, WpAdminPostAllPUI.DELETE_ELLIPSIS_MENU_BY_TITLE, postTitle);
		sleepInSecond(1);
		clickOnElement(driver, WpAdminPostAllPUI.ELLIPSIS_MENU_ITEM, menuItem);
		sleepInSecond(1);
	}
	
	public void clickonClosePreviewButton() {
		clickOnElement(driver, WpAdminPostAllPUI.CLOSE_PREVIEW_BUTTON);
	}
	
	public void clickonLikePreviewButton() {
		clickOnElement(driver, WpAdminPostAllPUI.LIKE_BUTTON_PREVIEW);
	}
	
	public void inputIntoSearchTextbox(String postTitle) {
		sendKeysToElement(driver, WpAdminPostAllPUI.SEARCH_TEXTBOX, postTitle);
		sleepInSecond(1);
	}

	public void selectPreviewOption(String previewOption) {
		sleepInSecond(1);
		clickOnElement(driver, WpAdminPostAllPUI.WEB_PREVIEW_DROPDOWN);
		sleepInSecond(1);
		clickOnElement(driver, WpAdminPostAllPUI.PREVIEW_OPTION, previewOption);
		sleepInSecond(1);
	}

	public void switchToWebPreviewIframe() {
		switchToFrameIframe(driver, WpAdminPostAllPUI.WEB_PREVIEW_IFRAME);
	}

	public boolean isPostTitlePreviewDisplayed(String postTitle) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.POST_TITLE_PREVIEW, postTitle);
	}

	public boolean isPostImagePreviewDisplayed(String uploadedImageName) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.POST_IMAGE_PREVIEW, uploadedImageName);
	}

	public boolean isPostBodyPreviewDisplayed(String postBody) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.POST_BODY_PREVIEW, postBody);
	}
	
	public boolean isPostCategoryPreviewDisplayed(String postCategory) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.POST_CATEGORY_PREVIEW, postCategory);
	}
	
	public boolean isPostPublishedDatePreviewDisplayed(String publishedDate) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.PUBLISHED_DATE_PREVIEW, publishedDate);
	}
	
	public boolean isPostAuthorPreviewDisplayed(String authorName) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.POST_AUTHOR_PREVIEW, authorName);
	}
	
	public boolean isPostTagPreviewDisplayed(String postTag) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.POST_TAG_PREVIEW, postTag);
	}

	public boolean isPostCommentPreviewDisplayed() {
		return isElementDisplayed(driver, WpAdminPostAllPUI.COMMENT_TEXTAREA_PREVIEW);
	}

	public boolean isPostCommentPreviewUndisplayed() {
		return isElementUndisplayed(driver, WpAdminPostAllPUI.COMMENT_TEXTAREA_PREVIEW);
	}
	
	public boolean isPostTitleDisplayed(String postTitle) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.POST_TITLE_LINK, postTitle);
	}
	
	public boolean isPostImageDisplayed(String postTitle, String uploadedImageName) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.IMG_BY_TITLE, postTitle, uploadedImageName);
	}
	
	public boolean isPostPublishedDateDisplayed(String postTitle, String publishedDate) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.PUBLISHED_DATE_BY_TITLE, postTitle, publishedDate);
	}
	
	public boolean isPostStickyTagDisplayed(String postTitle) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.STICKY_TAG_BY_TITLE, postTitle);
	}

	public boolean isPostLikedDisplayed(String postTitle) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.LIKED_BY_TITLE, postTitle);
	}
	
	public boolean isPostTrashedOrDeletedMessageDisplayed(String message) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.DELETE_MESSAGE, message);
	}

	public String getNoResultsMessage() {
		return getElementText(driver, WpAdminPostAllPUI.NO_RESULTS_MESSAGE);
	}

// -------------------------------------------------------------------------------------------------------------------------
	public WpAdminPostNewPO clickOnAddNewButton() {
		clickOnElement(driver, WpAdminPostAllPUI.ADD_NEW_BUTTON);
		return WpPageGeneratorManager.getAdminPostNewPage(driver);
	}

	public void inputIntoSearchTextbox1(String postTitle) {
		sendKeysToElement(driver, WpAdminPostAllPUI.SEARCH_TEXTBOX_1, postTitle);
	}

	public void clickOnSearchPostsButton() {
		clickOnElement(driver, WpAdminPostAllPUI.SEARCH_POSTS_BUTTON);
	}
	
	public String getNumberOfSearchResults() {
		return getElementText(driver, WpAdminPostAllPUI.NUMBER_SEARCH_RESULTS);
	}

	public boolean isPostInfoResultTableDisplayed(String postTitle, String columnName, String infoValue) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.POST_INFO_TEXT_BY_TITLE_AND_COLUMN_NAME, postTitle, columnName, infoValue);
	}
	
	public String getPostPublishedDate(String postTitle) {
		return getElementText(driver, WpAdminPostAllPUI.POST_PUBLISHED_DATE_BY_TITLE, postTitle);
	}
	
	public boolean isPostStickyDisplayed(String postTitle) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.POST_STICKY_BY_TITLE, postTitle);
	}
	
	public WpAdminPostNewPO clickOnPostTitleLink(String postTitle, String columnName) {
		clickOnElement(driver, WpAdminPostAllPUI.POST_INFO_TEXT_BY_TITLE_AND_COLUMN_NAME, postTitle, columnName, postTitle);
		return WpPageGeneratorManager.getAdminPostNewPage(driver);
	}

	public void checkPostTitleCheckbox(String postTitle) {
		checkCheckboxOrRadio(driver, WpAdminPostAllPUI.ROW_CHECKBOX_BY_POST_TITLE, postTitle);
	}

	public void selectItemFromBulkActionsDropdown(String itemValue) {
		selectOptionFromDefaultDropdown(driver, WpAdminPostAllPUI.BULK_ACTIONS_DROPDOWN, itemValue);
	}

	public void clickOnApplyButton() {
		clickOnElement(driver, WpAdminPostAllPUI.APPLY_BUTTON);
	}

	public boolean isDeletePostMessageDisplayed(String message) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.DELETE_POST_MESSAGE, message);
	}

	public boolean isNoPostsFoundMessageDisplayed(String message) {
		return isElementDisplayed(driver, WpAdminPostAllPUI.NO_POST_FOUND_MESSAGE, message);
	}

	public void clickOnTrashTab() {
		clickOnElement(driver, WpAdminPostAllPUI.TRASH_TAB);
	}

}
