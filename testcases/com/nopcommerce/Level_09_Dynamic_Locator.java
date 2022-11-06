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

public class Level_09_Dynamic_Locator extends NcBaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);

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
		userAddressesPage = (NcUserAddressesPO) userMyProductReviewsPage.openUserMyAccountPages_1(driver, "Addresses");
		Assert.assertTrue(userAddressesPage.isAddressesHeaderDisplayed());
		
		// Addresses -> Downloadable products
		userDownloadableProductsPage = (NcUserDownloadableProductsPO) userAddressesPage.openUserMyAccountPages_1(driver, "Downloadable products");
		Assert.assertTrue(userDownloadableProductsPage.isDownloadableProductsHeaderDisplayed());
		
		// Downloadable products -> Customer info
		userCustomerInfoPage = (NcUserCustomerInfoPO) userDownloadableProductsPage.openUserMyAccountPages_1(driver, "Customer info");
		Assert.assertTrue(userCustomerInfoPage.isCustomerInfoHeaderDisplayed());
	}
	
	@Test
	public void User_04_Dynamic_Page_02() {
		// Customer info -> My product reviews
		userCustomerInfoPage.openUserMyAccountPages_2(driver, "My product reviews");
		userMyProductReviewsPage = NcPageGeneratorManager.getUserMyProductReviewsPage(driver);
		Assert.assertTrue(userMyProductReviewsPage.isMyProductReviewsHeaderDisplayed());

		// My product reviews -> Reward points
		userMyProductReviewsPage.openUserMyAccountPages_2(driver, "Reward points");
		userRewardPointsPage = NcPageGeneratorManager.getUserRewardPointsPage(driver);
		Assert.assertTrue(userRewardPointsPage.isRewardPointsHeaderDisplayed());

		// Reward points -> Addresses
		userRewardPointsPage.openUserMyAccountPages_2(driver, "Addresses");
		userAddressesPage = NcPageGeneratorManager.getUserAddressesPage(driver);
		Assert.assertTrue(userAddressesPage.isAddressesHeaderDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private NcUserHomePO userHomePage;
	private NcUserRegisterPO userRegisterPage;
	private NcUserLoginPO userLoginPage;
	private NcUserCustomerInfoPO userCustomerInfoPage;
	private NcUserAddressesPO userAddressesPage;
	private NcUserOrdersPO userOrdersPage;
	private NcUserDownloadableProductsPO userDownloadableProductsPage;
	private NcUserRewardPointsPO userRewardPointsPage;
	private NcUserMyProductReviewsPO userMyProductReviewsPage;
	private String firstName, lastName, email, password;
	
}