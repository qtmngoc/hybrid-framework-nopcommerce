package pageObjects.jQuery.uploadFiles;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.jQuery.BasePage;
import pageUIs.jQuery.uploadFiles.HomePageUI;

public class HomePageObject extends BasePage{
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isFileLoadedByName(String fileName) {
		return isElementDisplayed(driver, HomePageUI.FILE_NAME_LOADED, fileName);
	}

	public void clickOnStartButton() {
		List<WebElement> startButtons = getListElements(driver, HomePageUI.START_BUTTON);
		
		for (WebElement button : startButtons) {
			button.click();
			sleepInSecond(1);
		} 
	}
	
	public boolean isFileUploadedLinkByName(String fileName) {
		return isElementDisplayed(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
	}

	public boolean isFileUploadedImageByName(String fileName) {
		waitElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
		return isImageLoadedByJS(driver, HomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
	}

}
