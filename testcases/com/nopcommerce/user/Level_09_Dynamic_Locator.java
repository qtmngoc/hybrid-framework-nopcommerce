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

public class Level_09_Dynamic_Locator extends BaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Dynamic";
		lastName = "Locator";
		email = "switchpage.level9" + generateFakeNumber() + "@mail.com";
		password = "1352022";
	}

	@Test
	public void User_01_Register_Login() {
		userRegisterPage = userHomePage.clickOnRegisterLink();

		userRegisterPage.inputIntoFirstNameTextbox(firstName);
		userRegisterPage.inputIntoLastNameTextbox(lastName);
		userRegisterPage.inputIntoEmailTextbox(email);
		userRegisterPage.inputIntoPasswordTextbox(password);
		userRegisterPage.inputIntoConfirmPasswordTextbox(password);
		userRegisterPage.clickOnRegisterButton();
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

		userHomePage = userRegisterPage.clickOnLogoutLink();
		userLoginPage = userHomePage.clickOnLoginLink();

		userLoginPage.inputIntoEmailTextbox(email);
		userLoginPage.inputIntoPasswordTextbox(password);

		userHomePage = userLoginPage.clickOnLoginButton();
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

		userCustomerInfoPage = userHomePage.clickOnMyAccountLink();
		Assert.assertTrue(userCustomerInfoPage.isCustomerInfoHeaderDisplayed());
	}

	@Test
	public void User_02_Switch_Page() {
		// Customer Info -> Reward points
		userRewardPointsPage = userCustomerInfoPage.openUserRewardPointsPage(driver);
		Assert.assertTrue(userRewardPointsPage.isRewardPointsHeaderDisplayed());
		
		// Reward points -> Orders
		userOrdersPage = userRewardPointsPage.openUserOrdersPage(driver);
		Assert.assertTrue(userOrdersPage.isOrdersHeaderDisplayed());
		
		// Orders -> My product reviews
		userMyProductReviewsPage = userOrdersPage.openUserMyProductReviewsPage(driver);
		Assert.assertTrue(userMyProductReviewsPage.isMyProductReviewsHeaderDisplayed());
	}
	
	@Test
	public void User_03_Dynamic_Page_01() {
		// My product reviews -> Addresses
		userAddressesPage = (UserAddressesPageObject) userMyProductReviewsPage.openUserMyAccountPages_1(driver, "Addresses");
		Assert.assertTrue(userAddressesPage.isAddressesHeaderDisplayed());
		
		// Addresses -> Downloadable products
		userDownloadableProductsPage = (UserDownloadableProductsPageObject) userAddressesPage.openUserMyAccountPages_1(driver, "Downloadable products");
		Assert.assertTrue(userDownloadableProductsPage.isDownloadableProductsHeaderDisplayed());
		
		// Downloadable products -> Customer info
		userCustomerInfoPage = (UserCustomerInfoPageObject) userDownloadableProductsPage.openUserMyAccountPages_1(driver, "Customer info");
		Assert.assertTrue(userCustomerInfoPage.isCustomerInfoHeaderDisplayed());
	}
	
	@Test
	public void User_04_Dynamic_Page_02() {
		// Customer info -> My product reviews
		userCustomerInfoPage.openUserMyAccountPages_2(driver, "My product reviews");
		userMyProductReviewsPage = PageGeneratorManager.getUserMyProductReviewsPage(driver);
		Assert.assertTrue(userMyProductReviewsPage.isMyProductReviewsHeaderDisplayed());

		// My product reviews -> Reward points
		userMyProductReviewsPage.openUserMyAccountPages_2(driver, "Reward points");
		userRewardPointsPage = PageGeneratorManager.getUserRewardPointsPage(driver);
		Assert.assertTrue(userRewardPointsPage.isRewardPointsHeaderDisplayed());

		// Reward points -> Addresses
		userRewardPointsPage.openUserMyAccountPages_2(driver, "Addresses");
		userAddressesPage = PageGeneratorManager.getUserAddressesPage(driver);
		Assert.assertTrue(userAddressesPage.isAddressesHeaderDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private UserAddressesPageObject userAddressesPage;
	private UserOrdersPageObject userOrdersPage;
	private UserDownloadableProductsPageObject userDownloadableProductsPage;
	private UserRewardPointsPageObject userRewardPointsPage;
	private UserMyProductReviewsPageObject userMyProductReviewsPage;
	private String firstName, lastName, email, password;
	
}