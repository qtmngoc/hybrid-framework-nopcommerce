package com.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.nopCommerce.NcBaseTest;
import commons.nopCommerce.NcGlobalConstants;
import commons.nopCommerce.NcPageGeneratorManager;
import pageObjects.nopCommerce.NcUserCustomerInfoPO;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserLoginPO;
import pageObjects.nopCommerce.NcUserRegisterPO;

public class Level_19_Pattern_Object extends NcBaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, NcGlobalConstants.USER_PAGE_URL);
		
		log.info("Pre-condition - Step 00: Open NopCommerce Home page");
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);

		gender = "Female";
		firstName = "Pattern";
		lastName = "Object";
		dayOfBirth = "3";
		monthOfBirth = "July";
		yearOfBirth = "2000";
		email = "patternobject.level19." + generateFakeNumber() + "@mail.com";
		companyName = "Automation FC";
		password = "030722";
	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01: Navigate to Register page");
		userHomePage.clickOnLinkByText(driver, "Register");
		userRegisterPage = NcPageGeneratorManager.getUserRegisterPage(driver);
		
		log.info("Register - Step 02: Select Gender radio button");
		userRegisterPage.selectRadioButtonByLabelText(driver, gender);

		log.info("Register - Step 03: Enter '" + firstName + "' into First Name text box");
		userRegisterPage.inputIntoTextboxById(driver, "FirstName", firstName);
		
		log.info("Register - Step 04: Enter '" + lastName + "' into Last Name text box");
		userRegisterPage.inputIntoTextboxById(driver, "LastName", lastName);
		
		log.info("Register - Step 05: Select Date Of Birth");
		userRegisterPage.selectDropdownByName(driver, "DateOfBirthDay", dayOfBirth);
		userRegisterPage.selectDropdownByName(driver, "DateOfBirthMonth", monthOfBirth);
		userRegisterPage.selectDropdownByName(driver, "DateOfBirthYear", yearOfBirth);
		
		log.info("Register - Step 06: Enter '" + email + "' into Email text box");
		userRegisterPage.inputIntoTextboxById(driver, "Email", email);
		
		log.info("Register - Step 07: Enter '" + companyName + "' into Company Name text box");
		userRegisterPage.inputIntoTextboxById(driver, "Company", companyName);
		
		log.info("Register - Step 08: Select Newsletter checkbox");
		userRegisterPage.selectCheckboxById(driver, "Newsletter");
		
		log.info("Register - Step 09: Enter '" + password + "' into Password text box");
		userRegisterPage.inputIntoTextboxById(driver, "Password", password);
		
		log.info("Register - Step 10: Enter '" + password + "' into Confirm Password text box");
		userRegisterPage.inputIntoTextboxById(driver, "ConfirmPassword", password);
		
		log.info("Register - Step 11: Click on Register button");
		userRegisterPage.clickOnButtonByText(driver, "Register");
		
		log.info("Register - Step 12: Verify registration success message is displayed");
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - Step 01: Navigate to Login page");
		userRegisterPage.clickOnLinkByText(driver, "Log in");
		userLoginPage = NcPageGeneratorManager.getUserLoginPage(driver);
		
		log.info("Login - Step 02: Enter '" + email + "' into Email text box");
		userLoginPage.inputIntoTextboxById(driver, "Email", email);
		
		log.info("Login - Step 03: Enter '" + password + "' into Password text box");
		userLoginPage.inputIntoTextboxById(driver, "Password", password);

		log.info("Login - Step 04: Click on Login button");
		userLoginPage.clickOnButtonByText(driver, "Log in");
		userHomePage = NcPageGeneratorManager.getUserHomePage(driver);
		
		log.info("Login - Step 05: Verify My Account link is displayed");
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}
	
	@Test
	public void User_03_My_Account() {
		log.info("My account - Step 01: Navigate to My Account page");
		userHomePage.clickOnLinkByText(driver, "My account");
		userCustomerInfoPage = NcPageGeneratorManager.getUserCustomerInfoPage(driver);
		
		log.info("My account - Step 02: Verify Customer Info page is displayed");
		Assert.assertTrue(userCustomerInfoPage.isCustomerInfoHeaderDisplayed());
		
		log.info("My account - Step 03: Verify Gender radio button is selected");
		Assert.assertTrue(userCustomerInfoPage.isRadioButtonSelectedByLabelText(driver, gender));
		
		log.info("My account - Step 04: Verify First Name value is correct");
		Assert.assertEquals(userCustomerInfoPage.getTextboxValueById(driver, "FirstName"), firstName);
		
		log.info("My account - Step 05: Verify Last Name value is correct");
		Assert.assertEquals(userCustomerInfoPage.getTextboxValueById(driver, "LastName"), lastName);
		
		log.info("My account - Step 06: Verify Date Of Birth value is correct");
		Assert.assertEquals(userCustomerInfoPage.getDropdownValueByName(driver, "DateOfBirthDay"), dayOfBirth);
		Assert.assertEquals(userCustomerInfoPage.getDropdownValueByName(driver, "DateOfBirthMonth"), "7");
		Assert.assertEquals(userCustomerInfoPage.getDropdownValueByName(driver, "DateOfBirthYear"), yearOfBirth);
		
		log.info("My account - Step 07: Verify Email value is correct");
		Assert.assertEquals(userCustomerInfoPage.getTextboxValueById(driver, "Email"), email);
		
		log.info("My account - Step 08: Verify Company Name value is correct");
		Assert.assertEquals(userCustomerInfoPage.getTextboxValueById(driver, "Company"), companyName);
		
		log.info("My account - Step 09: Verify Newsletter checkbox is selected");
		Assert.assertTrue(userCustomerInfoPage.isCheckboxSelectedById(driver, "Newsletter"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
	
	private WebDriver driver;
	private NcUserHomePO userHomePage;
	private NcUserRegisterPO userRegisterPage;
	private NcUserLoginPO userLoginPage;
	private NcUserCustomerInfoPO userCustomerInfoPage;
	private String gender, firstName, lastName, dayOfBirth, monthOfBirth, yearOfBirth, companyName, email, password;
	
}