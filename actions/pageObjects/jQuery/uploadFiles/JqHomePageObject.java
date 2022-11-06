package pageObjects.jQuery.uploadFiles;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.jQuery.JqBasePage;
import pageUIs.jQuery.uploadFiles.JqHomePageUI;

public class JqHomePageObject extends JqBasePage{
	WebDriver driver;

	public JqHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isFileLoadedByName(String fileName) {
		return isElementDisplayed(driver, JqHomePageUI.FILE_NAME_LOADED, fileName);
	}

	public void clickOnStartButton() {
		List<WebElement> startButtons = getListElements(driver, JqHomePageUI.START_BUTTON);
		
		for (WebElement button : startButtons) {
			button.click();
			sleepInSecond(1);
		} 
	}
	
	public boolean isFileUploadedLinkByName(String fileName) {
		return isElementDisplayed(driver, JqHomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
	}

	public boolean isFileUploadedImageByName(String fileName) {
		waitForElementVisible(driver, JqHomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
		return isImageLoadedByJS(driver, JqHomePageUI.FILE_NAME_UPLOADED_IMAGE, fileName);
	}

}
