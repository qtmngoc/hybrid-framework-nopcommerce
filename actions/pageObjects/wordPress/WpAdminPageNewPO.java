package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpGlobalConstants;
import commons.wordPress.WpPageGeneratorManager;
import pageUIs.wordPress.WpAdminPageNewPUI;

public class WpAdminPageNewPO extends WpBasePage {
	WebDriver driver;

	public WpAdminPageNewPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public void switchToPagePatternIframe() {
		switchToFrameIframe(driver, WpAdminPageNewPUI.PAGE_PATTERN_IFRAME);
	}

	public void clickOnBlankPageButton() {
		clickOnElement(driver, WpAdminPageNewPUI.BLANK_PAGE_BUTTON);
		sleepInSecond(2);
	}

	public void inputIntoPageTitle(String pageTitle) {
		sendKeysToElement(driver, WpAdminPageNewPUI.TITLE_TEXTBOX, pageTitle);
	}

	public void inputIntoPageBody(String pageBody) {
		clickOnElement(driver, WpAdminPageNewPUI.BODY_BUTTON);
		sendKeysToElement(driver, WpAdminPageNewPUI.BODY_TEXTAREA, pageBody);
	}
	
	public void inputIntoEditPageBody(String editBody) {
		clickOnElement(driver, WpAdminPageNewPUI.BODY_TEXTAREA);
		clearElementValueByDeleteKey(driver, WpAdminPageNewPUI.BODY_TEXTAREA);
		sendKeysToElement(driver, WpAdminPageNewPUI.BODY_TEXTAREA, editBody);
	}

	public void clickOnPageSidebar() {
		clickOnElement(driver, WpAdminPageNewPUI.PAGE_SIDEBAR);
	}

	public void clickOnPanelByText(String panelName) {
		clickOnElement(driver, WpAdminPageNewPUI.PANEL_BY_TEXT, panelName);
	}

	public void clickOnImageMenu(String menuName) {
		clickOnElement(driver, WpAdminPageNewPUI.IMAGE_MENU, menuName);
	}

	public void clickOnMediaLibraryItem() {
		sleepInSecond(1);
		clickOnElement(driver, WpAdminPageNewPUI.MEDIA_LIBRARY_ITEM);
	}

	public String getUploadedImageName(String propertyName) {
		waitForElementClickable(driver, WpAdminPageNewPUI.INSERT_IMAGE_BUTTON);
		return getElementAttribute(driver, WpAdminPageNewPUI.UPLOADED_IMAGE_NAME, propertyName);
	}

	public void clickOnInsertButton() {
		clickOnElement(driver, WpAdminPageNewPUI.INSERT_IMAGE_BUTTON);
	}

	public boolean isImageUploaded(String uploadedImageName) {
		return isElementDisplayed(driver, WpAdminPageNewPUI.UPLOADED_IMAGE, uploadedImageName);
	}

	public void checkAllowCommentsCheckbox() {
		checkCheckboxOrRadio(driver, WpAdminPageNewPUI.ALLOW_COMMENTS_CHECKBOX);
	}

	public void clickOnPublishOrUpdateButton() {
		clickOnElement(driver, WpAdminPageNewPUI.PUBLISH_OR_UPDATE_BUTTON);
	}

	public void clickOnPrePublishButton() {
		clickOnElement(driver, WpAdminPageNewPUI.PRE_PUBLISH_BUTTON);
	}

	public boolean isPagePublishedOrUpdatedMessageDisplayed(String message) {
		return isElementDisplayed(driver, WpAdminPageNewPUI.PUBLISHED_OR_UPDATED_MESSAGE, message);
	}

	public String getPageNowLiveMessage() {
		return getElementText(driver, WpAdminPageNewPUI.PAGE_NOW_LIVE_MESSAGE);
	}

	public void uploadPageImage(String pageImage) {
		getElement(driver, WpAdminPageNewPUI.ADD_NEW_IMAGE).sendKeys(WpGlobalConstants.UPLOAD_FILES + pageImage);
	}

	public void clickOnWordpressLogo() {
		clickOnElement(driver, WpAdminPageNewPUI.WORDPRESS_LOGO);
	}

	public WpAdminPageAllPO clickOnViewPagesLink() {
		clickOnElement(driver, WpAdminPageNewPUI.VIEW_PAGES_LINK);
		return WpPageGeneratorManager.getAdminPageAllPage(driver);
	}

	public void selectPageImage(String imageName) {
		clickOnElement(driver, WpAdminPageNewPUI.IMAGE_BUTTON, imageName);
	}

	public void clickOnDeleteImageIcon() {
		clickOnElement(driver, WpAdminPageNewPUI.DELETE_IMAGE_ICON);
	}

	public void clickOnSearchImageButton() {
		clickOnElement(driver, WpAdminPageNewPUI.OPEN_SEARCH_IMAGE_ICON);
	}

	public void clickOnConfirmDeleteImageButton() {
		clickOnElement(driver, WpAdminPageNewPUI.CONFIRM_DELETE_IMAGE_BUTTON);
	}
	
	public void inputIntoSearchImageTextbox(String imageName) {
		sendKeysToElement(driver, WpAdminPageNewPUI.SEARCH_IMAGE_TEXTBOX, imageName);
	}

	public void uncheckAllowCommentsCheckbox() {
		uncheckCheckbox(driver, WpAdminPageNewPUI.ALLOW_COMMENTS_CHECKBOX);
	}

	public String pagePublishedDateOnAdmin() {
		return String.valueOf(getCurrentYear()) + "-" + String.format("%02d", getCurrentMonth()) + "-" + String.format("%02d", getCurrentDay());
	}

	public String pagePublishedDateOnUser() {
		return String.format("%02d", getCurrentDay()) + "/" + String.format("%02d", getCurrentMonth()) + "/" + String.valueOf(getCurrentYear());
	}

}
