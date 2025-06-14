package com.facebook;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.facebook.FbBaseTest;
import commons.facebook.FbPageGeneratorManager;
import pageObjects.facebook.FbLoginPageObject;

public class Level_13_Element_Undisplayed extends FbBaseTest {
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		loginPage = FbPageGeneratorManager.getLoginPage(driver);
	}
	
	@Test
	public void Element_01_Displayed() {
		loginPage.clickOnCreateNewAccountButton();
		verifyTrue(loginPage.isEmailTextboxDisplayed());
	}
	
	@Test
	public void Element_02_Undisplayed_And_Exist_In_DOM() {
		loginPage.inputIntoEmailTextbox("level13@mail.com");
		loginPage.sleepInSecond(1);
		verifyTrue(loginPage.isConfirmEmailTextboxDisplayed());
		
		loginPage.inputIntoEmailTextbox("");
		loginPage.sleepInSecond(2);
		verifyTrue(loginPage.isConfirmEmailTextboxUndisplayed());
		//verifyFalse(loginPage.isConfirmEmailTextboxDisplayed()); -> not wait
	}

	@Test
	public void Element_03_Undisplayed_And_Not_Exist_In_DOM() {
		loginPage.clickCloseIconOnRegisterForm();
		loginPage.sleepInSecond(1);
		
		verifyTrue(loginPage.isConfirmEmailTextboxUndisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private FbLoginPageObject loginPage;
	
}