package commons.nopCommerce;

import org.openqa.selenium.WebDriver;

import pageObjects.nopCommerce.NcAdminConfigurationStoresPO;
import pageObjects.nopCommerce.NcAdminDashboardPO;
import pageObjects.nopCommerce.NcAdminLoginPO;
import pageObjects.nopCommerce.NcAdminReportsSalesSummaryPO;
import pageObjects.nopCommerce.NcUserAddressesPO;
import pageObjects.nopCommerce.NcUserCustomerInfoPO;
import pageObjects.nopCommerce.NcUserDownloadableProductsPO;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserMyProductReviewsPO;
import pageObjects.nopCommerce.NcUserOrdersPO;
import pageObjects.nopCommerce.NcUserRegisterPO;
import pageObjects.nopCommerce.NcUserRewardPointsPO;
import pageObjects.nopCommerce.NcUserSearchPO;

public class NcPageGeneratorManager {

	public static NcUserHomePO getUserHomePage(WebDriver driver) {
		return new NcUserHomePO(driver);
	}

	public static NcUserRegisterPO getUserRegisterPage(WebDriver driver) {
		return new NcUserRegisterPO(driver);
	}

	public static NcUserLoginPO getUserLoginPage(WebDriver driver) {
		return new NcUserLoginPO(driver);
	}

	public static NcUserCustomerInfoPO getUserCustomerInfoPage(WebDriver driver) {
		return new NcUserCustomerInfoPO(driver);
	}

	public static NcUserAddressesPO getUserAddressesPage(WebDriver driver) {
		return new NcUserAddressesPO(driver);
	}

	public static NcUserOrdersPO getUserOrdersPage(WebDriver driver) {
		return new NcUserOrdersPO(driver);
	}

	public static NcUserDownloadableProductsPO getUserDownloadableProductsPage(WebDriver driver) {
		return new NcUserDownloadableProductsPO(driver);
	}

	public static NcUserRewardPointsPO getUserRewardPointsPage(WebDriver driver) {
		return new NcUserRewardPointsPO(driver);
	}

	public static NcUserMyProductReviewsPO getUserMyProductReviewsPage(WebDriver driver) {
		return new NcUserMyProductReviewsPO(driver);
	}
	
	public static NcUserSearchPO getUserSearchPage(WebDriver driver) {
		return new NcUserSearchPO(driver);
	}

	public static NcAdminLoginPO getAdminLoginPage(WebDriver driver) {
		return new NcAdminLoginPO(driver);
	}

	public static NcAdminDashboardPO getAdminDashboardPage(WebDriver driver) {
		return new NcAdminDashboardPO(driver);
	}

	public static NcAdminConfigurationStoresPO getAdminConfigurationStoresPage(WebDriver driver) {
		return new NcAdminConfigurationStoresPO(driver);
	}

	public static NcAdminReportsSalesSummaryPO getAdminReportsSalesSummaryPage(WebDriver driver) {
		return new NcAdminReportsSalesSummaryPO(driver);
	}

}
