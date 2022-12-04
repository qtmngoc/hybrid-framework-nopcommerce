package com.wordpress;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.wordPress.WpBaseTest;
import commons.wordPress.WpPageGeneratorManager;
import pageObjects.wordPress.WpAdminCategoryPO;
import pageObjects.wordPress.WpAdminDashboardPO;
import pageObjects.wordPress.WpAdminLoginPO;
import reportConfig.wordPress.WpExtentTestManagerV5;

public class Demo_03_Category extends WpBaseTest {
	
	@Parameters({ "browser", "adminUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl) {	
		this.browserName = browserName;
		
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = WpPageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboardPage = adminLoginPage.loginToSystem(driver, Demo_01_Login.username, Demo_01_Login.password);
		
		adminCategoryPage = adminDashboardPage.clickOnPostsCategoriesMenuLink();
	}
	
	@Test
	public void Category_01_Create_New_Parent_Category(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Create a new parent category on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Posts' menu and 'Categories' submenu to go to Admin CATEGORY page.");
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Add New Category' button to open the dialog.");
		adminCategoryPage.clickOnAddNewCategoryButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + parentCatName + "\" into 'New Category Name' field.");
		adminCategoryPage.inputIntoCategoryNameTextbox(parentCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + parentCatDesc + "\" into 'Description' box.");
		adminCategoryPage.inputIntoCategoryDescriptionTextarea(parentCatDesc);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Top level Category' toggle is enabled.");
		verifyTrue(adminCategoryPage.isParentCategoryMessageDisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Add' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify newly created category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(parentCatName));

	}
	
	@Test
	public void Category_02_Create_New_Child_Category(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Create a new child category on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Refresh Admin CATEGORY page.");
		adminCategoryPage.refreshCurrentPage(driver);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Add New Category' button to open the dialog.");
		adminCategoryPage.clickOnAddNewCategoryButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + childCatName + "\" into 'New Category Name' field.");
		adminCategoryPage.inputIntoCategoryNameTextbox(childCatName);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + childCatDesc + "\" into 'Description' box.");
		adminCategoryPage.inputIntoCategoryDescriptionTextarea(childCatDesc);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Top level Category' toggle to disable it.");
		adminCategoryPage.clickOnTopLevelToggle();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + parentCatName + "\" into 'Search...' field and select it.");
		adminCategoryPage.inputIntoDialogSearchTextbox(parentCatName);
		adminCategoryPage.checkParentCategoryRadio(parentCatName);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Add' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify newly created category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(childCatName));
	}
	
	@Test
	public void Category_03_Edit_Child_Category(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Edit child category on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Refresh Admin CATEGORY page.");
		adminCategoryPage.refreshCurrentPage(driver);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + childCatName + "\" into 'Search...' field.");
		adminCategoryPage.inputIntoSearchTextbox(childCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Ellipsis menu and click on 'Edit' item.");
		adminCategoryPage.clickOnEllipsisMenu(childCatName, "Edit");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editChildCatName + "\" into 'New Category Name' field.");
		adminCategoryPage.inputIntoCategoryNameTextbox(editChildCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editChildCatDesc + "\" into 'Description' box.");
		adminCategoryPage.inputIntoCategoryDescriptionTextarea(editChildCatDesc);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Top level Category' toggle to enable it.");
		adminCategoryPage.clickOnTopLevelToggle();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'Top level Category' toggle is enabled.");
		verifyTrue(adminCategoryPage.isParentCategoryMessageDisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Update' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Close Search icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify edited category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(editChildCatName));
	}
	
	@Test
	public void Category_04_Edit_Parent_Category(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Edit parent category on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Refresh Admin CATEGORY page.");
		adminCategoryPage.refreshCurrentPage(driver);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + parentCatName + "\" into 'Search...' field.");
		adminCategoryPage.inputIntoSearchTextbox(parentCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Ellipsis menu and click on 'Edit' item.");
		adminCategoryPage.clickOnEllipsisMenu(parentCatName, "Edit");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editParentCatName + "\" into 'New Category Name' field.");
		adminCategoryPage.inputIntoCategoryNameTextbox(editParentCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editParentCatDesc + "\" into 'Description' box.");
		adminCategoryPage.inputIntoCategoryDescriptionTextarea(editParentCatDesc);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Top level Category' toggle to disable it.");
		adminCategoryPage.clickOnTopLevelToggle();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editChildCatName + "\" into 'Search...' field and select it.");
		adminCategoryPage.inputIntoDialogSearchTextbox(editChildCatName);
		adminCategoryPage.checkParentCategoryRadio(editChildCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'Update' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Close Search icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify edited category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(editParentCatName));
	}
	
	@Test
	public void Category_05_Delete_Category(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Delete categories on Admin site");
		int s = 0;

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Refresh Admin CATEGORY page.");
		adminCategoryPage.refreshCurrentPage(driver);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editChildCatName + "\" into 'Search...' field.");
		adminCategoryPage.inputIntoSearchTextbox(editChildCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Ellipsis menu and click on 'Delete' item.");
		adminCategoryPage.clickOnEllipsisMenu(editChildCatName, "Delete");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify confirm delete message contains \"" + editChildCatName + "\".");
		verifyTrue(adminCategoryPage.isConfirmDeleteMessageDisplayed(editChildCatName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'OK' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'No results found.' message is displayed.");
		verifyTrue(adminCategoryPage.isNoResultsFoundMessageDisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Close Search icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Enter \"" + editParentCatName + "\" into 'Search...' field.");
		adminCategoryPage.inputIntoSearchTextbox(editParentCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Open Ellipsis menu and click on 'Delete' item.");
		adminCategoryPage.clickOnEllipsisMenu(editParentCatName, "Delete");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify confirm delete message contains \"" + editParentCatName + "\".");
		verifyTrue(adminCategoryPage.isConfirmDeleteMessageDisplayed(editParentCatName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on 'OK' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify 'No results found.' message is displayed.");
		verifyTrue(adminCategoryPage.isNoResultsFoundMessageDisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Click on Close Search icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step " + String.format("%02d", s=s+1) + ": Verify deleted categories are undisplayed.");
		verifyTrue(adminCategoryPage.isCategoryUndisplayed(editChildCatName));
		verifyTrue(adminCategoryPage.isCategoryUndisplayed(editParentCatName));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	private String browserName;
	private int randomNumber3 = Demo_01_Login.randomNumber1;
	private String parentCatName = "Automation Parent " + randomNumber3;
	private String parentCatDesc = "test parent category " + randomNumber3;
	private String childCatName = "Automation Child " + randomNumber3;
	private String childCatDesc = "test child category " + randomNumber3;
	private String editParentCatName = "Automation Parent to Child " + randomNumber3;
	private String editParentCatDesc = "edit child " + randomNumber3;
	private String editChildCatName = "Automation Child to Parent " + randomNumber3;
	private String editChildCatDesc = "edit parent " + randomNumber3;
	
	private WebDriver driver;
	private WpAdminLoginPO adminLoginPage;
	private WpAdminDashboardPO adminDashboardPage;
	private WpAdminCategoryPO adminCategoryPage;
}