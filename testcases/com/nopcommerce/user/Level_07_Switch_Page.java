package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.BaseTest;
import commons.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserDownloadableProductsPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObjects.nopCommerce.user.UserOrdersPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;

public class Level_07_Switch_Page extends BaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

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
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressesPageObject addressesPage;
	private UserOrdersPageObject ordersPage;
	private UserDownloadableProductsPageObject downloadableProductsPage;
	private UserRewardPointsPageObject rewardPointsPage;
	private UserMyProductReviewsPageObject myProductReviewsPage;
	private String firstName, lastName, email, password;
	
}