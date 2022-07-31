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
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Click on Add New Category button to open dialog.");
		adminCategoryPage.clickOnAddNewCategoryButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Enter '" + parentCatName + "' into Name textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(parentCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter '" + parentCatDesc + "' into Description textbox.");
		adminCategoryPage.inputIntoCategoryDescriptionTextarea(parentCatDesc);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Verify Top level Category toggle is enabled.");
		verifyTrue(adminCategoryPage.isParentCategoryMessageDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Click on Add button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
	}
	
	@Test
	public void Category_02_Search_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Search newly created categories");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Enter '" + parentCatName + "' into Search textbox.");
		adminCategoryPage.inputIntoSearchTextbox(parentCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Verify '" + parentCatName + "' category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(parentCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Close Search result.");
		adminCategoryPage.clickOnCloseIcon();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter '" + childCatName + "' into Search textbox.");
		adminCategoryPage.inputIntoSearchTextbox(childCatName);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Verify '" + childCatName + "' category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(childCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Close Search result.");
		adminCategoryPage.clickOnCloseIcon();
	}
	
	@Test
	public void Category_03_Edit_Child_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Edit child category");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Scroll down to '" + childCatName + "' category.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(childCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open ellipsis menu and click on Edit.");
		adminCategoryPage.clickOnEllipsisMenu(childCatName, "Edit");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter '" + editChildCatName + "' into Name textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(editChildCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Enter '" + childCatDesc + "' into Description textbox.");
		adminCategoryPage.inputIntoCategoryDescriptionTextarea(childCatDesc);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Click on Top level Category to enable.");
		adminCategoryPage.clickOnTopLevelToggle();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify Top level Category toggle is enabled.");
		verifyTrue(adminCategoryPage.isParentCategoryMessageDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Click on Update button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Enter '" + editChildCatName + "' into Search textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editChildCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Verify '" + editChildCatName + "' category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(editChildCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Close Search result.");
		adminCategoryPage.clickOnCloseIcon();
	}
	
	@Test
	public void Category_04_Edit_Parent_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Edit parent category");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Scroll down to '" + parentCatName + "' category.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(parentCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open ellipsis menu and click on Edit.");
		adminCategoryPage.clickOnEllipsisMenu(parentCatName, "Edit");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Enter '" + editParentCatName + "' into Name textbox.");
		adminCategoryPage.inputIntoCategoryNameTextbox(editParentCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Click on Top level Category to disable.");
		adminCategoryPage.clickOnTopLevelToggle();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Select '" + editChildCatName + "' category.");
		adminCategoryPage.checkParentCategoryRadio(editChildCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Click on Update button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Enter '" + editParentCatName + "' into Search textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editParentCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Verify '" + editParentCatName + "' category is displayed.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(editParentCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Close Search result.");
		adminCategoryPage.clickOnCloseIcon();
	}
	
	@Test
	public void Category_05_Delete_Category(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Delete categories");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 01: Scroll down to '" + editChildCatName + "' category.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(editChildCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 02: Open ellipsis menu and click on Delete.");
		adminCategoryPage.clickOnEllipsisMenu(editChildCatName, "Delete");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 03: Verify confirm Delete message contains '" + editChildCatName + "'.");
		verifyTrue(adminCategoryPage.isConfirmDeleteMessageDisplayed(editChildCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 04: Click on Ok button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 05: Enter '" + editChildCatName + "' into Search textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editChildCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 06: Verify 'No results found.' message is displayed.");
		verifyTrue(adminCategoryPage.isNoResultsFoundMessageDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 07: Close Search result.");
		adminCategoryPage.clickOnCloseIcon();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 08: Scroll down to '" + editParentCatName + "' category.");
		verifyTrue(adminCategoryPage.isCategoryDisplayed(editParentCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 09: Open ellipsis menu and click on Delete.");
		adminCategoryPage.clickOnEllipsisMenu(editParentCatName, "Delete");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 10: Verify confirm Delete message contains '" + editParentCatName + "'.");
		verifyTrue(adminCategoryPage.isConfirmDeleteMessageDisplayed(editParentCatName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 11: Click on Ok button.");
		adminCategoryPage.clickOnAddOrUpdateOrOkButton();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 12: Enter '" + editParentCatName + "' into Search textbox.");
		adminCategoryPage.inputIntoSearchTextbox(editParentCatName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 13: Verify 'No results found.' message is displayed.");
		verifyTrue(adminCategoryPage.isNoResultsFoundMessageDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Step 14: Close Search result.");
		adminCategoryPage.clickOnCloseIcon();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	int randomNumber3 = Live_Coding_02_Post.randomNumber;
	String parentCatName = "Parent " + randomNumber3;
	String parentCatDesc = "test parent category " + randomNumber3;
	String childCatName = Live_Coding_02_Post.editCategory;
	String childCatDesc = "test child category " + randomNumber3;
	String editParentCatName = "Parent to Child " + randomNumber3;
	String editChildCatName = "Child to Parent " + randomNumber3;
	
	WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
	AdminCategoryPO adminCategoryPage;
}