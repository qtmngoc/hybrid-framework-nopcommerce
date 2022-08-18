package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.GlobalConstants;
import commons.wordPress.PageGeneratorManager;
import pageUIs.wordPress.AdminPageNewPUI;

public class AdminPageNewPO extends BasePage{
	WebDriver driver;

	public AdminPageNewPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public void switchToPagePatternIframe() {
		switchToFrameIframe(driver, AdminPageNewPUI.PAGE_PATTERN_IFRAME);
	}

	public void clickOnBlankPageButton() {
		clickOnElement(driver, AdminPageNewPUI.BLANK_PAGE_BUTTON);
		sleepInSecond(2);
	}

	public void inputIntoPageTitle(String pageTitle) {
		sendKeysToElement(driver, AdminPageNewPUI.TITLE_TEXTBOX, pageTitle);
	}

	public void inputIntoPageBody(String pageBody) {
		clickOnElement(driver, AdminPageNewPUI.BODY_BUTTON);
		sendKeysToElement(driver, AdminPageNewPUI.BODY_TEXTAREA, pageBody);
	}
	
	public void inputIntoEditPageBody(String editBody) {
		clickOnElement(driver, AdminPageNewPUI.BODY_TEXTAREA);
		clearElementValueByDeleteKey(driver, AdminPageNewPUI.BODY_TEXTAREA);
		sendKeysToElement(driver, AdminPageNewPUI.BODY_TEXTAREA, editBody);
	}

	public void clickOnPageSidebar() {
		clickOnElement(driver, AdminPageNewPUI.PAGE_SIDEBAR);
	}

	public void clickOnPanelByText(String panelName) {
		clickOnElement(driver, AdminPageNewPUI.PANEL_BY_TEXT, panelName);
	}

	public void clickOnImageMenu(String menuName) {
		clickOnElement(driver, AdminPageNewPUI.IMAGE_MENU, menuName);
	}

	public void clickOnMediaLibraryItem() {
		clickOnElement(driver, AdminPageNewPUI.MEDIA_LIBRARY_ITEM);
	}

	public String getUploadedImageName(String propertyName) {
		waitForElementClickable(driver, AdminPageNewPUI.INSERT_IMAGE_BUTTON);
		return getElementAttribute(driver, AdminPageNewPUI.UPLOADED_IMAGE_NAME, propertyName);
	}

	public void clickOnInsertButton() {
		clickOnElement(driver, AdminPageNewPUI.INSERT_IMAGE_BUTTON);
	}

	public boolean isImageUploaded(String uploadedImageName) {
		return isElementDisplayed(driver, AdminPageNewPUI.UPLOADED_IMAGE, uploadedImageName);
	}

	public void checkAllowCommentsCheckbox() {
		checkCheckboxOrRadio(driver, AdminPageNewPUI.ALLOW_COMMENTS_CHECKBOX);
	}

	public void clickOnPublishOrUpdateButton() {
		clickOnElement(driver, AdminPageNewPUI.PUBLISH_OR_UPDATE_BUTTON);
	}

	public void clickOnPrePublishButton() {
		clickOnElement(driver, AdminPageNewPUI.PRE_PUBLISH_BUTTON);
	}

	public boolean isPagePublishedOrUpdatedMessageDisplayed(String message) {
		return isElementDisplayed(driver, AdminPageNewPUI.PUBLISHED_OR_UPDATED_MESSAGE, message);
	}

	public String getPageNowLiveMessage() {
		return getElementText(driver, AdminPageNewPUI.PAGE_NOW_LIVE_MESSAGE);
	}

	public void uploadPageImage(String pageImage) {
		getElement(driver, AdminPageNewPUI.ADD_NEW_IMAGE).sendKeys(GlobalConstants.UPLOAD_FILES + pageImage);
	}

	public void clickOnWordpressLogo() {
		clickOnElement(driver, AdminPageNewPUI.WORDPRESS_LOGO);
	}

	public AdminPageAllPO clickOnAllPagesLink() {
		clickOnElement(driver, AdminPageNewPUI.ALL_PAGES_LINK);
		return PageGeneratorManager.getAdminPageAllPage(driver);
	}

	public void selectPageImage(String imageName) {
		clickOnElement(driver, AdminPageNewPUI.IMAGE_BUTTON, imageName);
	}

	public void clickOnDeleteImageIcon() {
		clickOnElement(driver, AdminPageNewPUI.DELETE_IMAGE_ICON);
	}

	public void clickOnSearchImageButton() {
		clickOnElement(driver, AdminPageNewPUI.OPEN_SEARCH_IMAGE_ICON);
	}

	public void clickOnConfirmDeleteImageButton() {
		clickOnElement(driver, AdminPageNewPUI.CONFIRM_DELETE_IMAGE_BUTTON);
	}
	
	public void inputIntoSearchImageTextbox(String imageName) {
		sendKeysToElement(driver, AdminPageNewPUI.SEARCH_IMAGE_TEXTBOX, imageName);
		
	}

	public void uncheckAllowCommentsCheckbox() {
		uncheckCheckbox(driver, AdminPageNewPUI.ALLOW_COMMENTS_CHECKBOX);
	}




}
