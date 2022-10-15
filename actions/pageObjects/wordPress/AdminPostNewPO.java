package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.PageGeneratorManager;
import pageUIs.wordPress.AdminPostNewPUI;

public class AdminPostNewPO extends BasePage{
	WebDriver driver;

	public AdminPostNewPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnPublishOrUpdateButton() {
		clickOnElement(driver, AdminPostNewPUI.PUBLISH_OR_UPDATE_BUTTON);
	}
	
	public void clickOnPrePublishButton() {
		clickOnElement(driver, AdminPostNewPUI.PRE_PUBLISH_BUTTON);
	}
	
	public void clickOnWordpressLogo() {
		clickOnElement(driver, AdminPostNewPUI.WORDPRESS_LOGO);
	}
	
	public void clickOnPostSidebar() {
		clickOnElement(driver, AdminPostNewPUI.POST_SIDEBAR);
	}
	
	public void clickOnPanelByText(String panelName) {
		clickOnElement(driver, AdminPostNewPUI.PANEL_BY_TEXT, panelName);
	}
	
	public void clickOnMediaLibraryItem() {
		clickOnElement(driver, AdminPostNewPUI.MEDIA_LIBRARY_ITEM);
	}
	
	public void clickOnUploadFilesTab() {
		clickOnElement(driver, AdminPostNewPUI.UPLOAD_FILE_TAB);
	}
	
	public void clickOnSetImageButton() {
		clickOnElement(driver, AdminPostNewPUI.SET_FEATURED_IMAGE_BUTTON);
	}
	
	public void clickOnAddNewCategoryForm() {
		clickOnElement(driver, AdminPostNewPUI.ADD_NEW_CATEGORY_FORM);
	}
	
	public void clickOnAddNewCategoryButton() {
		clickOnElement(driver, AdminPostNewPUI.ADD_NEW_CATEGORY_BUTTON);
	}
	
	public void clickOnImageMenu(String textValue) {
		clickOnElement(driver, AdminPostNewPUI.IMAGE_MENU, textValue);
	}
	
	public void clickOnDeleteImageButton() {
		clickOnElement(driver, AdminPostNewPUI.DELETE_IMAGE_BUTTON);
	}

	public void clickOnRemoveTagButton(String postTag) {
		clickOnElement(driver, AdminPostNewPUI.REMOVE_TAG_BUTTON, postTag);
	}
	
	public AdminPostAllPO clickOnAllPostsLink() {
		clickOnElement(driver, AdminPostNewPUI.ALL_POSTS_LINK);
		return PageGeneratorManager.getAdminPostAllPage(driver);
	}
	
	public void checkCheckboxByLabel(String labelText) {
		checkCheckboxOrRadio(driver, AdminPostNewPUI.CHECKBOX_BY_LABEL, labelText);
	}
	
	public void uncheckCheckboxByLabel(String labelText) {
		uncheckCheckbox(driver, AdminPostNewPUI.CHECKBOX_BY_LABEL, labelText);
	}
	
	public String getUploadedImageName() {
		waitForElementVisible(driver, AdminPostNewPUI.DELETE_IMAGE_BUTTON);
		return getElementText(driver, AdminPostNewPUI.UPLOADED_IMAGE_NAME);
	}
	
	public void inputIntoPostTitle(String postTitle) {
		sendKeysToElement(driver, AdminPostNewPUI.TITLE_TEXTBOX, postTitle);
	}
	
	public void inputIntoPostBody(String postBody) {
		clickOnElement(driver, AdminPostNewPUI.BODY_BUTTON);
		sendKeysToElement(driver, AdminPostNewPUI.BODY_TEXTAREA, postBody);
	}
	
	public void inputIntoEditPostBody(String editBody) {
		clickOnElement(driver, AdminPostNewPUI.BODY_TEXTAREA);
		clearElementValueByDeleteKey(driver, AdminPostNewPUI.BODY_TEXTAREA);
		sendKeysToElement(driver, AdminPostNewPUI.BODY_TEXTAREA, editBody);
	}
	
	public void inputIntoSearchCategoryTextbox(String categoryName) {
		sendKeysToElement(driver, AdminPostNewPUI.SEARCH_CATEGORY_TEXTBOX, categoryName);
	}
	
	public void inputIntoAddNewTagTextbox(String tagName) {
		sendKeysToElement(driver, AdminPostNewPUI.ADD_NEW_TAG_TEXTBOX, tagName + ",");
		// pressKeyOnElement(driver, AdminPostNewPUI.ADD_NEW_TAG_TEXTBOX, Keys.ENTER);
	}
	
	public void inputIntoNewCategoryTextbox(String categoryName) {
		sendKeysToElement(driver, AdminPostNewPUI.NEW_CATEGORY_TEXTBOX, categoryName);
	}
	
	public void inputIntoSearchImageTextbox(String imageName) {
		sendKeysToElement(driver, AdminPostNewPUI.SEARCH_IMAGE_TEXTBOX, imageName);
	}

	public boolean isPostPublishedOrUpdatedMessageDisplayed(String postPublishedMessage) {
		return isElementDisplayed(driver, AdminPostNewPUI.PUBLISHED_OR_UPDATED_MESSAGE, postPublishedMessage);
	}
	
	public boolean isImageUploaded(String uploadedImageName) {
		return isElementDisplayed(driver, AdminPostNewPUI.UPLOADED_IMAGE, uploadedImageName);
	}

	public AdminPostAllPO openAllPostsPage(String allPostsUrl) {
		openPageUrl(driver, allPostsUrl);
		return PageGeneratorManager.getAdminPostAllPage(driver);
	}

	public void selectItemFromParentCategoryDropdown(String categoryItem) {
		selectOptionFromDefaultDropdown(driver, AdminPostNewPUI.PARENT_CATEGORY_DROPDOWN, categoryItem);
	}

	public void selectPostImage(String postImage) {
		clickOnElement(driver, AdminPostNewPUI.IMAGE_CHECKBOX, postImage);
	}

	public boolean isRemoveTagButtonDisplayed(String postTag) {
		return isElementDisplayed(driver, AdminPostNewPUI.REMOVE_TAG_BUTTON, postTag);
	}

}
