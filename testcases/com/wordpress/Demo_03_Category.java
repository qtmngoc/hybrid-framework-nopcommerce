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
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Create new parent category");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on 'Add New Category' button to open dialog.");
		adminCategoryPage.clickOnAddNewCategoryButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + parentCatName + "\" into 'New Category Name' textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(parentCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + parentCatDesc + "\" into 'Description' textbox.");
		adminCategoryPage.inputIntoCategoryDescriptionTextarea(parentCatDesc);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Verify 'Top level Category' toggle is enabled.");
		verifyTrue(adminCategoryPage.isParentCategoryMessageDisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Click on 'Add' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
	}
	
	@Test
	public void Category_02_Create_New_Child_Category(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Create new child category");

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on 'Add New Category' button to open dialog.");
		adminCategoryPage.clickOnAddNewCategoryButton();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + childCatName + "\" into 'New Category Name' textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(childCatName);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + childCatDesc + "\" into 'Description' textbox.");
		adminCategoryPage.inputIntoCategoryDescriptionTextarea(childCatDesc);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Click on 'Top level Category' to disable.");
		adminCategoryPage.clickOnTopLevelToggle();

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Enter \"" + parentCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoDialogSearchTextbox(parentCatName);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Select \"" + parentCatName + "\" category checkbox.");
		adminCategoryPage.checkParentCategoryRadio(parentCatName);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on 'Add' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
	}
	
	@Test
	public void Category_03_Search_Category(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search newly created categories");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + parentCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(parentCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Verify \"" + parentCatName + "\" category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(parentCatName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + childCatName + "\" into Search textbox.");
		adminCategoryPage.inputIntoSearchTextbox(childCatName);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify \"" + childCatName + "\" category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(childCatName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
	}
	
	@Test
	public void Category_04_Edit_Child_Category(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Edit child category");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + childCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(childCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Ellipsis' menu and click on 'Edit' item.");
		adminCategoryPage.clickOnEllipsisMenu(childCatName, "Edit");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + editChildCatName + "\" into 'New Category Name' textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(editChildCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Click on 'Top level Category' to enable.");
		adminCategoryPage.clickOnTopLevelToggle();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify 'Top level Category' toggle is enabled.");
		verifyTrue(adminCategoryPage.isParentCategoryMessageDisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Update' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
	}
	
	@Test
	public void Category_05_Edit_Parent_Category(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Edit parent category");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + parentCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(parentCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Ellipsis' menu and click on 'Edit' item.");
		adminCategoryPage.clickOnEllipsisMenu(parentCatName, "Edit");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + editParentCatName + "\" into 'New Category Name' textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(editParentCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Click on 'Top level Category' to disable.");
		adminCategoryPage.clickOnTopLevelToggle();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Select \"" + editChildCatName + "\" category checkbox.");
		adminCategoryPage.checkParentCategoryRadio(editChildCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Update' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
	}
	
	@Test
	public void Category_06_Search_Category_After_Edit(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Search edited categories");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + editChildCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editChildCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Verify \"" + editChildCatName + "\" category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(editChildCatName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + editParentCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editParentCatName);

		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify \"" + editParentCatName + "\" category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(editParentCatName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
	}
	
	@Test
	public void Category_07_Delete_Category(Method method) {
		WpExtentTestManagerV5.startTest(method.getName() + " - " + browserName, "Delete categories");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + editChildCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editChildCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Ellipsis' menu and click on 'Delete' item.");
		adminCategoryPage.clickOnEllipsisMenu(editChildCatName, "Delete");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify confirm delete message contains \"" + editChildCatName + "\".");
		verifyTrue(adminCategoryPage.isConfirmDeleteMessageDisplayed(editChildCatName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Click on 'OK' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify 'No results found.' message is displayed.");
		verifyTrue(adminCategoryPage.isNoResultsFoundMessageDisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Enter \"" + editParentCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editParentCatName);
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Open 'Ellipsis' menu and click on 'Delete' item.");
		adminCategoryPage.clickOnEllipsisMenu(editParentCatName, "Delete");
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Verify confirm delete message contains \"" + editParentCatName + "\".");
		verifyTrue(adminCategoryPage.isConfirmDeleteMessageDisplayed(editParentCatName));
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Click on 'OK' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify 'No results found.' message is displayed.");
		verifyTrue(adminCategoryPage.isNoResultsFoundMessageDisplayed());
		
		WpExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	String browserName;
	int randomNumber3 = Demo_01_Login.randomNumber1;
	String parentCatName = "Parent " + randomNumber3;
	String parentCatDesc = "test parent category " + randomNumber3;
	String childCatName = "Child " + randomNumber3;
	String childCatDesc = "test child category " + randomNumber3;
	String editParentCatName = "Parent to Child " + randomNumber3;
	String editChildCatName = "Child to Parent " + randomNumber3;
	
	WebDriver driver;
	WpAdminLoginPO adminLoginPage;
	WpAdminDashboardPO adminDashboardPage;
	WpAdminCategoryPO adminCategoryPage;
}