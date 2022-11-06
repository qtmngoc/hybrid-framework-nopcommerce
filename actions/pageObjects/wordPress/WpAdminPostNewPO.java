package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpPageGeneratorManager;
import pageUIs.wordPress.WpAdminPostNewPUI;

public class WpAdminPostNewPO extends WpBasePage{
	WebDriver driver;

	public WpAdminPostNewPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnPublishOrUpdateButton() {
		clickOnElement(driver, WpAdminPostNewPUI.PUBLISH_OR_UPDATE_BUTTON);
	}
	
	public void clickOnPrePublishButton() {
		clickOnElement(driver, WpAdminPostNewPUI.PRE_PUBLISH_BUTTON);
	}
	
	public void clickOnWordpressLogo() {
		clickOnElement(driver, WpAdminPostNewPUI.WORDPRESS_LOGO);
	}
	
	public void clickOnPostSidebar() {
		clickOnElement(driver, WpAdminPostNewPUI.POST_SIDEBAR);
	}
	
	public void clickOnPanelByText(String panelName) {
		scrollToElementByJS(driver, WpAdminPostNewPUI.PANEL_BY_TEXT, panelName);
		clickOnElement(driver, WpAdminPostNewPUI.PANEL_BY_TEXT, panelName);
	}
	
	public void clickOnMediaLibraryItem() {
		clickOnElement(driver, WpAdminPostNewPUI.MEDIA_LIBRARY_ITEM);
	}
	
	public void clickOnUploadFilesTab() {
		clickOnElement(driver, WpAdminPostNewPUI.UPLOAD_FILE_TAB);
	}
	
	public void clickOnSetImageButton() {
		clickOnElement(driver, WpAdminPostNewPUI.SET_FEATURED_IMAGE_BUTTON);
	}
	
	public void clickOnAddNewCategoryForm() {
		clickOnElement(driver, WpAdminPostNewPUI.ADD_NEW_CATEGORY_FORM);
	}
	
	public void clickOnAddNewCategoryButton() {
		clickOnElement(driver, WpAdminPostNewPUI.ADD_NEW_CATEGORY_BUTTON);
	}
	
	public void clickOnImageMenu(String textValue) {
		clickOnElement(driver, WpAdminPostNewPUI.IMAGE_MENU, textValue);
	}
	
	public void clickOnDeleteImageButton() {
		clickOnElement(driver, WpAdminPostNewPUI.DELETE_IMAGE_BUTTON);
	}

	public void clickOnRemoveTagButton(String postTag) {
		clickOnElement(driver, WpAdminPostNewPUI.REMOVE_TAG_BUTTON, postTag);
	}
	
	public WpAdminPostAllPO clickOnAllPostsLink() {
		clickOnElement(driver, WpAdminPostNewPUI.ALL_POSTS_LINK);
		return WpPageGeneratorManager.getAdminPostAllPage(driver);
	}
	
	public void checkCheckboxByLabel(String labelText) {
		checkCheckboxOrRadio(driver, WpAdminPostNewPUI.CHECKBOX_BY_LABEL, labelText);
	}
	
	public void uncheckCheckboxByLabel(String labelText) {
		uncheckCheckbox(driver, WpAdminPostNewPUI.CHECKBOX_BY_LABEL, labelText);
	}
	
	public String getUploadedImageName() {
		waitForElementVisible(driver, WpAdminPostNewPUI.DELETE_IMAGE_BUTTON);
		return getElementText(driver, WpAdminPostNewPUI.UPLOADED_IMAGE_NAME);
	}
	
	public void inputIntoPostTitle(String postTitle) {
		sendKeysToElement(driver, WpAdminPostNewPUI.TITLE_TEXTBOX, postTitle);
	}
	
	public void inputIntoPostBody(String postBody) {
		clickOnElement(driver, WpAdminPostNewPUI.BODY_BUTTON);
		sendKeysToElement(driver, WpAdminPostNewPUI.BODY_TEXTAREA, postBody);
	}
	
	public void inputIntoEditPostBody(String editBody) {
		clickOnElement(driver, WpAdminPostNewPUI.BODY_TEXTAREA);
		clearElementValueByDeleteKey(driver, WpAdminPostNewPUI.BODY_TEXTAREA);
		sendKeysToElement(driver, WpAdminPostNewPUI.BODY_TEXTAREA, editBody);
	}
	
	public void inputIntoSearchCategoryTextbox(String categoryName) {
		sendKeysToElement(driver, WpAdminPostNewPUI.SEARCH_CATEGORY_TEXTBOX, categoryName);
	}
	
	public void inputIntoAddNewTagTextbox(String tagName) {
		sendKeysToElement(driver, WpAdminPostNewPUI.ADD_NEW_TAG_TEXTBOX, tagName + ",");
		// pressKeyOnElement(driver, AdminPostNewPUI.ADD_NEW_TAG_TEXTBOX, Keys.ENTER);
	}
	
	public void inputIntoNewCategoryTextbox(String categoryName) {
		sendKeysToElement(driver, WpAdminPostNewPUI.NEW_CATEGORY_TEXTBOX, categoryName);
	}
	
	public void inputIntoSearchImageTextbox(String imageName) {
		sendKeysToElement(driver, WpAdminPostNewPUI.SEARCH_IMAGE_TEXTBOX, imageName);
	}

	public boolean isPostPublishedOrUpdatedMessageDisplayed(String postPublishedMessage) {
		return isElementDisplayed(driver, WpAdminPostNewPUI.PUBLISHED_OR_UPDATED_MESSAGE, postPublishedMessage);
	}
	
	public boolean isImageUploaded(String uploadedImageName) {
		return isElementDisplayed(driver, WpAdminPostNewPUI.UPLOADED_IMAGE, uploadedImageName);
	}

	public WpAdminPostAllPO openAllPostsPage(String allPostsUrl) {
		openPageUrl(driver, allPostsUrl);
		return WpPageGeneratorManager.getAdminPostAllPage(driver);
	}

	public void selectItemFromParentCategoryDropdown(String categoryItem) {
		selectOptionFromDefaultDropdown(driver, WpAdminPostNewPUI.PARENT_CATEGORY_DROPDOWN, categoryItem);
	}

	public void selectPostImage(String postImage) {
		clickOnElement(driver, WpAdminPostNewPUI.IMAGE_CHECKBOX, postImage);
	}

	public boolean isRemoveTagButtonDisplayed(String postTag) {
		return isElementDisplayed(driver, WpAdminPostNewPUI.REMOVE_TAG_BUTTON, postTag);
	}

}
