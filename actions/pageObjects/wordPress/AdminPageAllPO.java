package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.PageGeneratorManager;
import pageUIs.wordPress.AdminPageAllPUI;

public class AdminPageAllPO extends BasePage{
	WebDriver driver;

	public AdminPageAllPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPageNewPO clickOnAddNewPageButton() {
		clickOnElement(driver, AdminPageAllPUI.ADD_NEW_PAGE_BUTTON);
		return PageGeneratorManager.getAdminPageNewPage(driver);
	}

	public void clickOnOpenSearchIcon() {
		clickOnElement(driver, AdminPageAllPUI.OPEN_SEARCH_ICON);
	}
	

	public void clickOnCloseSearchIcon() {
		clickOnElement(driver, AdminPageAllPUI.CLOSE_SEARCH_ICON);
	}

	public void inputIntoSearchTextbox(String pageTitle) {
		sendKeysToElement(driver, AdminPageAllPUI.SEARCH_TEXTBOX, pageTitle);
		sleepInSecond(1);
	}

	public void clickOnEllipsisMenu(String pageTitle, String menuItem) {
		clickOnElement(driver, AdminPageAllPUI.ELLIPSIS_MENU, pageTitle);
		sleepInSecond(1);
		clickOnElement(driver, AdminPageAllPUI.ELLIPSIS_MENU_ITEM, menuItem);
		sleepInSecond(1);
	}

	public void clickOnDeleteEllipsisMenu(String pageTitle, String menuItem) {
		clickOnElement(driver, AdminPageAllPUI.DELETE_ELLIPSIS_MENU, pageTitle);
		sleepInSecond(1);
		clickOnElement(driver, AdminPageAllPUI.ELLIPSIS_MENU_ITEM, menuItem);
		sleepInSecond(1);
	}

	public void selectPreviewOption(String previewOption) {
		clickOnElement(driver, AdminPageAllPUI.WEB_PREVIEW_DROPDOWN);
		sleepInSecond(1);
		clickOnElement(driver, AdminPageAllPUI.PREVIEW_OPTION, previewOption);
		sleepInSecond(1);
	}

	public void switchToWebPreviewIframe() {
		switchToFrameIframe(driver, AdminPageAllPUI.WEB_PREVIEW_IFRAME);
	}

	public boolean isPageTitlePreviewDisplayed(String pageTitle) {
		return isElementDisplayed(driver, AdminPageAllPUI.PAGE_TITLE_PREVIEW, pageTitle);
	}

	public boolean isPageImagePreviewDisplayed(String pageTitle, String uploadedImageName) {
		return isElementDisplayed(driver, AdminPageAllPUI.PAGE_IMAGE_PREVIEW, pageTitle, uploadedImageName);
	}

	public boolean isPageBodyPreviewDisplayed(String pageBody) {
		return isElementDisplayed(driver, AdminPageAllPUI.PAGE_BODY_PREVIEW, pageBody);
	}

	public boolean isCommentPreviewDisplayed() {
		return isElementDisplayed(driver, AdminPageAllPUI.COMMENT_TEXTAREA_PREVIEW);
	}

	public boolean isCommentPreviewUndisplayed() {
		return isElementUndisplayed(driver, AdminPageAllPUI.COMMENT_TEXTAREA_PREVIEW);
	}
	
	public void clickonClosePreviewButton() {
		clickOnElement(driver, AdminPageAllPUI.CLOSE_PREVIEW_BUTTON);
	}

	public boolean isPageTitleDisplayed(String pageTitle) {
		return isElementDisplayed(driver, AdminPageAllPUI.PAGE_TITLE_TEXT, pageTitle);
	}

	public void clickOnPageTitleLink(String pageTitle) {
		clickOnElement(driver, AdminPageAllPUI.PAGE_TITLE_TEXT, pageTitle);		
	}

	public UserPageDetailPO clickOnVisitSiteButton() {
		clickOnElement(driver, AdminPageAllPUI.VISIT_SITE_BUTTON);
		return PageGeneratorManager.getUserPageDetailPage(driver);
	}

	public boolean isPageTrashedOrDeletedMessageDisplayed(String message) {
		return isElementDisplayed(driver, AdminPageAllPUI.DELETE_MESSAGE, message);
	}

	public String getNoResultsMessage() {
		return getElementText(driver, AdminPageAllPUI.NO_RESULTS_MESSAGE);
	}

}
