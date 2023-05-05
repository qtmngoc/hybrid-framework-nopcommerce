package com.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_End_User;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcPageGeneratorManager;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserSearchPO;

public class Level_17_Share_Data_End_User extends NcBaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		email = Common_01_Register_End_User.email;
		password = Common_01_Register_End_User.password;
		nonexistentValue = "Macbook Pro 2050";
		relativeProductName = "Lenovo";
		absoluteProductName = "ThinkPad X1 Carbon";

		log.info("Pre-condition - Step 01: Open Home page and navigate to Login page");
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);
		userLoginPage = userHomePage.clickOnLoginLink();

		log.info("Pre-condition - Step 02: Enter '" + email + "' into Email text box");
		userLoginPage.inputIntoEmailTextbox(email);

		log.info("Pre-condition - Step 03: Enter '" + password + "' into Password text box");
		userLoginPage.inputIntoPasswordTextbox(password);

		log.info("Pre-condition - Step 04: Click on Log In button");
		userHomePage = userLoginPage.clickOnLoginButton();

		log.info("Pre-condition - Step 05: Navigate to Search page");
		userSearchPage = userHomePage.clickOnSearchLink();

		log.info("Pre-condition - Step 06: Verify Search header is displayed");
		verifyTrue(userSearchPage.isSearchHeaderDisplayed());
	}

	@Test
	public void Search_01_Empty_Value() {
		log.info("Search_01_Empty_Value - Step 01: Enter empty value into Search text box");
		userSearchPage.inputIntoSearchTextbox("");

		log.info("Search_01_Empty_Value - Step 02: Click on Search button");
		userSearchPage.clickOnSearchButton();
		
		log.info("Search_01_Empty_Value - Step 03: Verify warning message is displayed");
		verifyEquals(userSearchPage.getWarningMessage(), "Search term minimum length is 3 characters");
	}

	@Test
	public void Search_02_Nonexistent_Value() {
		log.info("Search_02_Nonexistent_Value - Step 01: Enter '" + nonexistentValue + "' into Search text box");
		userSearchPage.inputIntoSearchTextbox(nonexistentValue);
		
		log.info("Search_02_Nonexistent_Value - Step 02: Click on Search button");
		userSearchPage.clickOnSearchButton();
		
		log.info("Search_02_Nonexistent_Value - Step 03: Verify no result message is displayed");
		verifyEquals(userSearchPage.getNoResultMessage(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_03_Relative_Product_Name() {
		log.info("Search_03_Relative_Product_Name - Step 01: Enter '" + relativeProductName + "' into Search text box");
		userSearchPage.inputIntoSearchTextbox(relativeProductName);

		log.info("Search_03_Relative_Product_Name - Step 02: Click on Search button");
		userSearchPage.clickOnSearchButton();
		
		log.info("Search_03_Relative_Product_Name - Step 03: Verify realtive Product Name is displayed");
		for (WebElement name : userSearchPage.getRelativeProductNameResults()) {
			verifyTrue(name.getText().toLowerCase().contains(relativeProductName.toLowerCase()));
		}
	}

	@Test
	public void Search_04_Absolute_Product_Name() {
		log.info("Search_04_Absolute_Product_Name - Step 01: Enter '" + absoluteProductName + "' into Search text box");
		userSearchPage.inputIntoSearchTextbox(absoluteProductName);

		log.info("Search_04_Absolute_Product_Name - Step 02: Click on Search button");
		userSearchPage.clickOnSearchButton();
		
		log.info("Search_04_Absolute_Product_Name - Step 03: Verify absolute Product Name is displayed");
		verifyTrue(userSearchPage.getAbsoluteProductNameResult().getText().toLowerCase().contains(absoluteProductName.toLowerCase()));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private NcUserHomePO userHomePage;
	private NcUserLoginPO userLoginPage;
	private NcUserSearchPO userSearchPage;
	private String email, password, nonexistentValue, relativeProductName, absoluteProductName;

}