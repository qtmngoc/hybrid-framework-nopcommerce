package com.wordpress;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.wordPress.BaseTest;
import commons.wordPress.PageGeneratorManager;
import pageObjects.wordPress.AdminCategoryPO;
import pageObjects.wordPress.AdminDashboardPO;
import pageObjects.wordPress.AdminLoginPO;
import reportConfig.wordPress.ExtentTestManagerV5;

public class Live_Coding_03_Category extends BaseTest {
	
	@Parameters({ "browser", "adminUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl) {		
		driver = getBrowserDriver(browserName, adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboardPage = adminLoginPage.loginToSystem(driver, Live_Coding_01_Login.username, Live_Coding_01_Login.password);
		
		adminCategoryPage = adminDashboardPage.clickOnPostsCategoriesMenuLink();
	}
	
	@Test
	public void Category_01_Create_New_Parent_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Create new parent category");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on 'Add New Category' button to open dialog.");
		adminCategoryPage.clickOnAddNewCategoryButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + parentCatName + "\" into 'New Category Name' textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(parentCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + parentCatDesc + "\" into 'Description' textbox.");
		adminCategoryPage.inputIntoCategoryDescriptionTextarea(parentCatDesc);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Verify 'Top level Category' toggle is enabled.");
		verifyTrue(adminCategoryPage.isParentCategoryMessageDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Click on 'Add' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
	}
	
	@Test
	public void Category_02_Create_New_Child_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Create new child category");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on 'Add New Category' button to open dialog.");
		adminCategoryPage.clickOnAddNewCategoryButton();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter \"" + childCatName + "\" into 'New Category Name' textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(childCatName);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + childCatDesc + "\" into 'Description' textbox.");
		adminCategoryPage.inputIntoCategoryDescriptionTextarea(childCatDesc);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Click on 'Top level Category' to disable.");
		adminCategoryPage.clickOnTopLevelToggle();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Enter \"" + parentCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoDialogSearchTextbox(parentCatName);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Select \"" + parentCatName + "\" category checkbox.");
		adminCategoryPage.checkParentCategoryRadio(parentCatName);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on 'Add' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
	}
	
	@Test
	public void Category_03_Search_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Search newly created categories");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + parentCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(parentCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Verify \"" + parentCatName + "\" category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(parentCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + childCatName + "\" into Search textbox.");
		adminCategoryPage.inputIntoSearchTextbox(childCatName);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify \"" + childCatName + "\" category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(childCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
	}
	
	@Test
	public void Category_04_Edit_Child_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Edit child category");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + childCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(childCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Ellipsis' menu and click on 'Edit'.");
		adminCategoryPage.clickOnEllipsisMenu(childCatName, "Edit");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + editChildCatName + "\" into 'New Category Name' textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(editChildCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Click on 'Top level Category' to enable.");
		adminCategoryPage.clickOnTopLevelToggle();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify 'Top level Category' toggle is enabled.");
		verifyTrue(adminCategoryPage.isParentCategoryMessageDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Update' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
	}
	
	@Test
	public void Category_05_Edit_Parent_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Edit parent category");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + parentCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(parentCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Ellipsis' menu and click on 'Edit'.");
		adminCategoryPage.clickOnEllipsisMenu(parentCatName, "Edit");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter \"" + editParentCatName + "\" into 'New Category Name' textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(editParentCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Click on 'Top level Category' to disable.");
		adminCategoryPage.clickOnTopLevelToggle();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Select \"" + editChildCatName + "\" category checkbox.");
		adminCategoryPage.checkParentCategoryRadio(editChildCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Update' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
	}
	
	@Test
	public void Category_06_Search_Category_After_Edit(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Search edited categories");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + editChildCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editChildCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Verify \"" + editChildCatName + "\" category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(editChildCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter \"" + editParentCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editParentCatName);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify \"" + editParentCatName + "\" category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(editParentCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
	}
	
	@Test
	public void Category_07_Delete_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Delete categories");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter \"" + editChildCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editChildCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open 'Ellipsis' menu and click on 'Delete'.");
		adminCategoryPage.clickOnEllipsisMenu(editChildCatName, "Delete");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify confirm delete message contains \"" + editChildCatName + "\".");
		verifyTrue(adminCategoryPage.isConfirmDeleteMessageDisplayed(editChildCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Click on 'OK' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify 'No results found.' message is displayed.");
		verifyTrue(adminCategoryPage.isNoResultsFoundMessageDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Enter \"" + editParentCatName + "\" into 'Search' textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editParentCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Open 'Ellipsis' menu and click on 'Delete'.");
		adminCategoryPage.clickOnEllipsisMenu(editParentCatName, "Delete");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Verify confirm delete message contains \"" + editParentCatName + "\".");
		verifyTrue(adminCategoryPage.isConfirmDeleteMessageDisplayed(editParentCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Click on 'OK' button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Verify 'No results found.' message is displayed.");
		verifyTrue(adminCategoryPage.isNoResultsFoundMessageDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Click on 'Close Search' icon.");
		adminCategoryPage.clickOnCloseSearchIcon();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	int randomNumber3 = Live_Coding_02_Post.randomNumber2;
	String parentCatName = "Parent " + randomNumber3;
	String parentCatDesc = "test parent category " + randomNumber3;
	String childCatName = "Child " + randomNumber3;
	String childCatDesc = "test child category " + randomNumber3;
	String editParentCatName = "Parent to Child " + randomNumber3;
	String editChildCatName = "Child to Parent " + randomNumber3;
	
	WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
	AdminCategoryPO adminCategoryPage;
}