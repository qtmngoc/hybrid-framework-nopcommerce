package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnRegisterLink() {
		waitElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickOnElement(driver, HomePageUI.REGISTER_LINK);
	}

}
