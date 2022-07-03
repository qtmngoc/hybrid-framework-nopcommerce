package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.BaseTest;
import commons.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserSearchPageObject;

public class Level_18_Custom_Close_Driver extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Close";
		lastName = "Driver";
		email = "closedriver.level17." + generateFakeNumber() + "@mail.com";
		password = "010722";
		nonexistentValue = "Macbook Pro 2050";
		relativeProductName = "Lenovo";
		absoluteProductName = "ThinkPad X1 Carbon";
		
		log.info("Pre-condition - Step 01: Navigate to Register page");
		userRegisterPage = userHomePage.clickOnRegisterLink();

		log.info("Pre-condition - Step 02: Enter '" + firstName + "' into First Name text box");
		userRegisterPage.inputIntoFirstNameTextbox(firstName);
		
		log.info("Pre-condition - Step 03: Enter '" + lastName + "' into Last Name text box");
		userRegisterPage.inputIntoLastNameTextbox(lastName);
		
		log.info("Pre-condition - Step 04: Enter '" + email + "' into Email text box");
		userRegisterPage.inputIntoEmailTextbox(email);
		
		log.info("Pre-condition - Step 05: Enter '" + password + "' into Password text box");
		userRegisterPage.inputIntoPasswordTextbox(password);
		
		log.info("Pre-condition - Step 06: Enter '" + password + "' into Confirm Password text box");
		userRegisterPage.inputIntoConfirmPasswordTextbox(password);
		
		log.info("Pre-condition - Step 07: Click on Register button");
		userRegisterPage.clickOnRegisterButton();
		
		log.info("Pre-condition - Step 08: Verify registration success message is displayed");
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed...");

		log.info("Pre-condition - Step 09: Navigate to Search page");
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
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserSearchPageObject userSearchPage;
	private String firstName, lastName, email, password, nonexistentValue, relativeProductName, absoluteProductName;

}