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

	public void inputIntoPostTitle(String postTitle) {
		sendKeysToElement(driver, AdminPostNewPUI.TITLE_TEXTBOX, postTitle);
	}
	
	public void inputIntoPostBody(String postBody) {
		clickOnElement(driver, AdminPostNewPUI.BODY_BUTTON);
		sendKeysToElement(driver, AdminPostNewPUI.BODY_TEXTBOX, postBody);
	}

	public void clickOnPublishButton(String postBody) {
		clickOnElement(driver, AdminPostNewPUI.PUBLISH_BUTTON);
	}
	
	public void clickOnPrePublishButton(String postBody) {
		clickOnElement(driver, AdminPostNewPUI.PRE_PUBLISH_BUTTON);
	}

	public boolean isPostPublishedMessageDisplayed(String postPublishedMessage) {
		return isElementDisplayed(driver, AdminPostNewPUI.PUBLISHED_MESSAGE, postPublishedMessage);
	}

	public AdminPostAllPO openAllPostsPage(String allPostsUrl) {
		openPageUrl(driver, allPostsUrl);
		return PageGeneratorManager.getAdminPostAllPage(driver);
	}

	public void clickOnWordpressButton() {
		clickOnElement(driver, AdminPostNewPUI.WORDPRESS_BUTTON);
	}

	public AdminPostAllPO clickOnAllPostsLink() {
		clickOnElement(driver, AdminPostNewPUI.ALL_POSTS_LINK);
		return PageGeneratorManager.getAdminPostAllPage(driver);
	}

}
