package pageObjects.wordPress;

import org.openqa.selenium.WebDriver;

import commons.wordPress.BasePage;
import commons.wordPress.PageGeneratorManager;
import pageUIs.wordPress.AdminPostAllPUI;

public class AdminPostAllPO extends BasePage{
	WebDriver driver;

	public AdminPostAllPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostNewPO clickOnAddNewButton() {
		clickOnElement(driver, AdminPostAllPUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.getAdminPostNewPage(driver);
	}

	public void inputIntoSearchTextbox(String postTitle) {
		//sendKeysToElement(driver, admin, value);
	}

	public void clickOnSearchPostsButton() {
		// TODO Auto-generated method stub
		
	}

	public boolean isSearchResultTableDisplayed(String columnName, String value) {
		// TODO Auto-generated method stub
		return false;
	}

}
