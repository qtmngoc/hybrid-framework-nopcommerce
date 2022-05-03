package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.BaseTest;
import commons.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.AddressesPageObject;
import pageObjects.nopCommerce.CustomerInfoPageObject;
import pageObjects.nopCommerce.DownloadableProductsPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.MyProductReviewsPageObject;
import pageObjects.nopCommerce.OrdersPageObject;
import pageObjects.nopCommerce.RegisterPageObject;
import pageObjects.nopCommerce.RewardPointsPageObject;

public class Level_07_Switch_Page extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		firstName = "Switch";
		lastName = "Page";
		email = "switchpage.level7" + generateFakeNumber() + "@mail.vn";
		password = "352022";
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

		homePage = registerPage.clickOnLogoutLink();
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
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}

	@Test
	public void User_04_Switch_Page() {
		// Customer Info -> Reward points
		rewardPointsPage = customerInfoPage.openRewardPointsPage(driver);
		Assert.assertTrue(rewardPointsPage.isRewardPointsPageDisplayed());

		// Reward points -> Orders
		ordersPage = rewardPointsPage.openOrdersPage(driver);
		Assert.assertTrue(ordersPage.isOrdersPageDisplayed());

		// Orders -> My product reviews
		myProductReviewsPage = ordersPage.openMyProductReviewsPage(driver);
		Assert.assertTrue(myProductReviewsPage.isMyProductReviewsPageDisplayed());

		// My product reviews - > Addresses
		addressesPage = myProductReviewsPage.openAddressesPage(driver);
		Assert.assertTrue(addressesPage.isAddressesPageDisplayed());

		// Addresses -> Downloadable products
		downloadableProductsPage = addressesPage.openDownloadableProductsPage(driver);
		Assert.assertTrue(downloadableProductsPage.isDownloadableProductsPageDisplayed());

		// Downloadable products -> Customer Info
		customerInfoPage = downloadableProductsPage.openCustomerInfoPage(driver);
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private AddressesPageObject addressesPage;
	private OrdersPageObject ordersPage;
	private DownloadableProductsPageObject downloadableProductsPage;
	private RewardPointsPageObject rewardPointsPage;
	private MyProductReviewsPageObject myProductReviewsPage;
	private String firstName, lastName, email, password;
}