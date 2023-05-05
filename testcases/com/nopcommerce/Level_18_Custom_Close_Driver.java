package com.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcPageGeneratorManager;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserSearchPO;

public class Level_18_Custom_Close_Driver extends NcBaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		nonexistentValue = "Macbook Pro 2050";
		relativeProductName = "Lenovo";
		absoluteProductName = "ThinkPad X1 Carbon";
		
		log.info("Pre-condition - Step 01: Open Home page and navigate to Search page");
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);
		userSearchPage = userHomePage.clickOnSearchLink();

		log.info("Pre-condition - Step 10: Verify Search header is displayed");
		Assert.assertTrue(userSearchPage.isSearchHeaderDisplayed());
	}

	@Test
	public void Search_01_Empty_Value() {
		log.info("Search - Step 01: Enter empty value into Search text box");
		userSearchPage.inputIntoSearchTextbox("");

		log.info("Search - Step 02: Click on Search button");
		userSearchPage.clickOnSearchButton();
		
		log.info("Search - Step 03: Verify warning message is displayed");
		Assert.assertEquals(userSearchPage.getWarningMessage(), "Search term minimum length is 3 characters");
	}

	@Test
	public void Search_02_Nonexistent_Value() {
		log.info("Search - Step 01: Enter '" + nonexistentValue + "' into Search text box");
		userSearchPage.inputIntoSearchTextbox(nonexistentValue);
				
		log.info("Search - Step 02: Click on Search button");
		userSearchPage.clickOnSearchButton();
		
		log.info("Search - Step 03: Verify no result message is displayed");
		Assert.assertEquals(userSearchPage.getNoResultMessage(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_03_Relative_Product_Name() {
		log.info("Search - Step 01: Enter '" + relativeProductName + "' into Search text box");
		userSearchPage.inputIntoSearchTextbox(relativeProductName);

		log.info("Search - Step 02: Click on Search button");
		userSearchPage.clickOnSearchButton();
		
		log.info("Search - Step 03: Verify realtive Product Name is displayed");
		for (WebElement name : userSearchPage.getRelativeProductNameResults()) {
			Assert.assertTrue(name.getText().toLowerCase().contains(relativeProductName.toLowerCase()));
		}
	}

	@Test
	public void Search_04_Absolute_Product_Name() {
		log.info("Search - Step 01: Enter '" + absoluteProductName + "' into Search text box");
		userSearchPage.inputIntoSearchTextbox(absoluteProductName);

		log.info("Search - Step 02: Click on Search button");
		userSearchPage.clickOnSearchButton();
		
		log.info("Search - Step 03: Verify absolute Product Name is displayed");
		Assert.assertTrue(userSearchPage.getAbsoluteProductNameResult().getText().toLowerCase().contains(absoluteProductName.toLowerCase()));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private NcUserHomePO userHomePage;
	private NcUserSearchPO userSearchPage;
	private String nonexistentValue, relativeProductName, absoluteProductName;

}