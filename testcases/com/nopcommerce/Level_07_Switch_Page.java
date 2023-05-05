package com.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcPageGeneratorManager;
import pageObjects.nopCommerce.NcUserAddressesPO;
import pageObjects.nopCommerce.NcUserCustomerInfoPO;
import pageObjects.nopCommerce.NcUserDownloadableProductsPO;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserMyProductReviewsPO;
import pageObjects.nopCommerce.NcUserOrdersPO;
import pageObjects.nopCommerce.NcUserRegisterPO;
import pageObjects.nopCommerce.NcUserRewardPointsPO;

public class Level_07_Switch_Page extends NcBaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = NcPageGeneratorManager.getUserHomePage(driver);

		firstName = "Switch";
		lastName = "Page";
		email = "switchpage.level7" + generateFakeNumber() + "@mail.vn";
		password = "532022";
	}

	@Test
	public void User_01_Register() {
		registerPage = homePage.clickOnRegisterLink();

		registerPage.inputIntoFirstNameTextbox(firstName);
		registerPage.inputIntoLastNameTextbox(lastName);
		registerPage.inputIntoEmailTextbox(email);
		registerPage.inputIntoPasswordTextbox(password);
		registerPage.inputIntoConfirmPasswordTextbox(password);
		registerPage.clickOnRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.clickOnLoginLink();

		loginPage.inputIntoEmailTextbox(email);
		loginPage.inputIntoPasswordTextbox(password);

		homePage = loginPage.clickOnLoginButton();
	}

	@Test
	public void User_03_Customer_Info() {
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

		customerInfoPage = homePage.clickOnMyAccountLink();
		Assert.assertTrue(customerInfoPage.isCustomerInfoHeaderDisplayed());
	}

	@Test
	public void User_04_Switch_Page() {
		// Customer Info -> Reward points
		rewardPointsPage = customerInfoPage.openUserRewardPointsPage(driver);
		Assert.assertTrue(rewardPointsPage.isRewardPointsHeaderDisplayed());

		// Reward points -> Orders
		ordersPage = rewardPointsPage.openUserOrdersPage(driver);
		Assert.assertTrue(ordersPage.isOrdersHeaderDisplayed());

		// Orders -> My product reviews
		myProductReviewsPage = ordersPage.openUserMyProductReviewsPage(driver);
		Assert.assertTrue(myProductReviewsPage.isMyProductReviewsHeaderDisplayed());

		// My product reviews - > Addresses
		addressesPage = myProductReviewsPage.openUserAddressesPage(driver);
		Assert.assertTrue(addressesPage.isAddressesHeaderDisplayed());

		// Addresses -> Downloadable products
		downloadableProductsPage = addressesPage.openUserDownloadableProductsPage(driver);
		Assert.assertTrue(downloadableProductsPage.isDownloadableProductsHeaderDisplayed());

		// Downloadable products -> Customer Info
		customerInfoPage = downloadableProductsPage.openUserCustomerInfoPage(driver);
		Assert.assertTrue(customerInfoPage.isCustomerInfoHeaderDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private NcUserHomePO homePage;
	private NcUserRegisterPO registerPage;
	private NcUserLoginPO loginPage;
	private NcUserCustomerInfoPO customerInfoPage;
	private NcUserAddressesPO addressesPage;
	private NcUserOrdersPO ordersPage;
	private NcUserDownloadableProductsPO downloadableProductsPage;
	private NcUserRewardPointsPO rewardPointsPage;
	private NcUserMyProductReviewsPO myProductReviewsPage;
	private String firstName, lastName, email, password;
	
}