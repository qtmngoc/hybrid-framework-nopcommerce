package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.WpBasePage;
import commons.wordPress.WpPageGeneratorManager;
import pageUIs.wordPress.WpAdminPageAllPUI;

public class WpAdminPageAllPO extends WpBasePage {
	WebDriver driver;

	public WpAdminPageAllPO(WebDriver driver) {
		this.driver = driver;
	}

	public WpAdminPageNewPO clickOnAddNewPageButton() {
		clickOnElement(driver, WpAdminPageAllPUI.ADD_NEW_PAGE_BUTTON);
		return WpPageGeneratorManager.getAdminPageNewPage(driver);
	}

	public void clickOnOpenSearchIcon() {
		clickOnElement(driver, WpAdminPageAllPUI.OPEN_SEARCH_ICON);
	}
	
	public void clickOnCloseSearchIcon() {
		clickOnElement(driver, WpAdminPageAllPUI.CLOSE_SEARCH_ICON);
	}

	public void inputIntoSearchTextbox(String pageTitle) {
		sendKeysToElement(driver, WpAdminPageAllPUI.SEARCH_TEXTBOX, pageTitle);
		sleepInSecond(1);
	}

	public void clickOnEllipsisMenu(String pageTitle, String menuItem) {
		clickOnElement(driver, WpAdminPageAllPUI.ELLIPSIS_MENU_BY_TITLE, pageTitle);
		sleepInSecond(1);
		clickOnElement(driver, WpAdminPageAllPUI.ELLIPSIS_MENU_ITEM, menuItem);
		sleepInSecond(1);
	}

	public void clickOnDeleteEllipsisMenu(String pageTitle, String menuItem) {
		clickOnElement(driver, WpAdminPageAllPUI.DELETE_ELLIPSIS_MENU_BY_TITLE, pageTitle);
		sleepInSecond(1);
		clickOnElement(driver, WpAdminPageAllPUI.ELLIPSIS_MENU_ITEM, menuItem);
		sleepInSecond(1);
	}

	public void selectPreviewOption(String previewOption) {
		sleepInSecond(1);
		clickOnElement(driver, WpAdminPageAllPUI.WEB_PREVIEW_DROPDOWN);
		sleepInSecond(1);
		clickOnElement(driver, WpAdminPageAllPUI.PREVIEW_OPTION, previewOption);
		sleepInSecond(1);
	}

	public void switchToWebPreviewIframe() {
		switchToFrameIframe(driver, WpAdminPageAllPUI.WEB_PREVIEW_IFRAME);
	}

	public boolean isPageTitlePreviewDisplayed(String pageTitle) {
		return isElementDisplayed(driver, WpAdminPageAllPUI.PAGE_TITLE_PREVIEW, pageTitle);
	}

	public boolean isPageImagePreviewDisplayed(String uploadedImageName) {
		return isElementDisplayed(driver, WpAdminPageAllPUI.PAGE_IMAGE_PREVIEW, uploadedImageName);
	}

	public boolean isPageBodyPreviewDisplayed(String pageBody) {
		return isElementDisplayed(driver, WpAdminPageAllPUI.PAGE_BODY_PREVIEW, pageBody);
	}

	public boolean isPageCommentPreviewDisplayed() {
		return isElementDisplayed(driver, WpAdminPageAllPUI.COMMENT_TEXTAREA_PREVIEW);
	}

	public boolean isCommentPreviewUndisplayed() {
		return isElementUndisplayed(driver, WpAdminPageAllPUI.COMMENT_TEXTAREA_PREVIEW);
	}
	
	public void clickonClosePreviewButton() {
		clickOnElement(driver, WpAdminPageAllPUI.CLOSE_PREVIEW_BUTTON);
	}

	public boolean isPageTitleDisplayed(String pageTitle) {
		return isElementDisplayed(driver, WpAdminPageAllPUI.PAGE_TITLE_TEXT, pageTitle);
	}

	public WpAdminPageNewPO clickOnPageTitleLink(String pageTitle) {
		clickOnElement(driver, WpAdminPageAllPUI.PAGE_TITLE_TEXT, pageTitle);	
		return WpPageGeneratorManager.getAdminPageNewPage(driver);
	}

	public WpUserPageDetailPO clickOnVisitSiteButton() {
		clickOnElement(driver, WpAdminPageAllPUI.VISIT_SITE_BUTTON);
		return WpPageGeneratorManager.getUserPageDetailPage(driver);
	}

	public boolean isPageTrashedOrDeletedMessageDisplayed(String message) {
		return isElementDisplayed(driver, WpAdminPageAllPUI.DELETE_MESSAGE, message);
	}

	public String getNoResultsMessage() {
		return getElementText(driver, WpAdminPageAllPUI.NO_RESULTS_MESSAGE);
	}

}
