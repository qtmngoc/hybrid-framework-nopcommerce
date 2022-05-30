package commons.nopCommerce;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.admin.AdminConfigurationStoresPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.admin.AdminReportsSalesSummaryPageObject;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserDownloadableProductsPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObjects.nopCommerce.user.UserOrdersPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;
import pageUIs.nopCommerce.user.BasePageUI;

public class BasePage {

	public static BasePage getBasePage() {
		return new BasePage();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private WebDriverWait explicitWait(WebDriver driver) {
		return new WebDriverWait(driver, longTimeout);
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitAlertPresent(WebDriver driver) {
		return explicitWait(driver).until(ExpectedConditions.alertIsPresent());
	}

	public void accepAlert(WebDriver driver) {
		waitAlertPresent(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresent(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitAlertPresent(driver).getText();
	}

	public void sendKeysToAlert(WebDriver driver, String textValue) {
		waitAlertPresent(driver).sendKeys(textValue);
	}

	public Set<String> getAllWindowIDs(WebDriver driver) {
		return driver.getWindowHandles();
	}

	public void switchToWindowByID(WebDriver driver, String currentWindowID) {
		for (String id : getAllWindowIDs(driver)) {
			if (!id.equals(currentWindowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String expectedWindowTitle) {
		for (String id : getAllWindowIDs(driver)) {
			driver.switchTo().window(id);
			String currentWindowTitle = getPageTitle(driver);
			if (currentWindowTitle.equals(expectedWindowTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentWindowID) {
		for (String id : getAllWindowIDs(driver)) {
			if (!id.equals(parentWindowID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentWindowID);
		}
	}

	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=")) {
			by = By.id(locatorType.substring(4));
		} else if (locatorType.startsWith("class=")) {
			by = By.className(locatorType.substring(7));
		} else if (locatorType.startsWith("name=")) {
			by = By.name(locatorType.substring(6));
		} else if (locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(5));
		} else if (locatorType.startsWith("xpath=")) {
			by = By.xpath(locatorType.substring(7));
		} else {
			throw new RuntimeException("Locator Type is not supported!");
		}
		return by;
	}

	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	private WebElement getElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	private List<WebElement> getListElements(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public void clickOnElement(WebDriver driver, String locatorType) {
		waitElementClickable(driver, locatorType).click();
	}

	public void clickOnElement(WebDriver driver, String locatorType, String... dynamicValues) {
		waitElementClickable(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}

	public void sendKeysToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = waitElementVisible(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendKeysToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = waitElementVisible(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectOptionInDefaultDropdown(WebDriver driver, String locatorType, String textOption) {
		Select select = new Select(waitElementVisible(driver, locatorType));
		select.selectByValue(textOption);
	}

	public void selectOptionInDefaultDropdown(WebDriver driver, String locatorType, String textOption, String... dynamicValues) {
		Select select = new Select(waitElementVisible(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByValue(textOption);
	}

	public String getSelectedOptionTextInDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(waitElementVisible(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectedOptionTextInDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
		Select select = new Select(waitElementVisible(driver, getDynamicXpath(locatorType, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isMultiSelectDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(waitElementVisible(driver, locatorType));
		return select.isMultiple();
	}

	public void selectOptionInCustomDropdown(WebDriver driver, String parentLocatorType, String childLocatorType, String expectedTextOption) {
		clickOnElement(driver, parentLocatorType);
		sleepInSecond(1);
		List<WebElement> allOptions = explicitWait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocatorType)));
		for (WebElement option : allOptions) {
			if (option.getText().trim().equals(expectedTextOption)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
				sleepInSecond(1);
				option.click();
				break;
			}
		}
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return waitElementVisible(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return waitElementVisible(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locatorType) {
		return waitElementVisible(driver, locatorType).getText();
	}

	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return waitElementVisible(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return waitElementVisible(driver, locatorType).getCssValue(propertyName);
	}

	public String getHexColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getListElementsSize(WebDriver driver, String locatorType) {
		return getListElements(driver, locatorType).size();
	}

	public int getListElementsSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListElements(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	public void checkCheckboxRadio(WebDriver driver, String locatorType) {
		if (!getElement(driver, locatorType).isSelected()) {
			clickOnElement(driver, locatorType);
		}
	}

	public void uncheckCheckbox(WebDriver driver, String locatorType) {
		if (getElement(driver, locatorType).isSelected()) {
			clickOnElement(driver, locatorType);
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return waitElementVisible(driver, locatorType).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return waitElementVisible(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getElement(driver, locatorType).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return waitElementVisible(driver, locatorType).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(waitElementVisible(driver, locatorType));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void mouseHoverOnElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(waitElementVisible(driver, locatorType)).perform();
	}

	public void rightClickOnElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.contextClick(waitElementClickable(driver, locatorType)).perform();
	}

	public void doubleClickOnElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.doubleClick(waitElementClickable(driver, locatorType)).perform();
	}

	public void dragDropElement(WebDriver driver, String sourceLocatorType, String targetLocatorType) {
		Actions action = new Actions(driver);
		action.dragAndDrop(waitElementVisible(driver, sourceLocatorType), waitElementVisible(driver, targetLocatorType)).perform();
	}

	public void sendKeypressToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(waitElementVisible(driver, locatorType), key).perform();
	}

	public void scrollToBottomPageByJS(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickOnElementByJS(WebDriver driver, String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locatorType));
	}

	public void scrollToElementByJS(WebDriver driver, String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locatorType));
	}

	public void removeAttributeInDOMByJS(WebDriver driver, String locatorType, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locatorType));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait(driver).until(jQueryLoad) && explicitWait(driver).until(jsLoad);
	}

	public String getElementValidationMessageByJS(WebDriver driver, String locatorType) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locatorType));
	}

	public boolean isImageLoadedByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getShadowDOMByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;", getElement(driver, locatorType));
		return element;
	}
	
	public WebElement waitElementVisible(WebDriver driver, String locatorType) {
		return explicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	public WebElement waitElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		return explicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitAllElementsVisible(WebDriver driver, String locatorType) {
		explicitWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	public void waitElementInvisible(WebDriver driver, String locatorType) {
		explicitWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		explicitWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitAllElementsInvisible(WebDriver driver, String locatorType) {
		explicitWait(driver).until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, locatorType)));
	}

	public WebElement waitElementClickable(WebDriver driver, String locatorType) {
		return explicitWait(driver).until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public WebElement waitElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		return explicitWait(driver).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	// Level 07: Switch Page
	public UserCustomerInfoPageObject openUserCustomerInfoPage(WebDriver driver) {
		clickOnElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserAddressesPageObject openUserAddressesPage(WebDriver driver) {
		clickOnElement(driver, BasePageUI.ADDRESSES_LINK);
		return PageGeneratorManager.getUserAddressesPage(driver);
	}

	public UserOrdersPageObject openUserOrdersPage(WebDriver driver) {
		clickOnElement(driver, BasePageUI.ORDERS_LINK);
		return PageGeneratorManager.getUserOrdersPage(driver);
	}

	public UserDownloadableProductsPageObject openUserDownloadableProductsPage(WebDriver driver) {
		clickOnElement(driver, BasePageUI.DOWNLOADABLE_PRODUCTS_LINK);
		return PageGeneratorManager.getUserDownloadableProductsPage(driver);
	}

	public UserRewardPointsPageObject openUserRewardPointsPage(WebDriver driver) {
		clickOnElement(driver, BasePageUI.REWARD_POINTS_LINK);
		return PageGeneratorManager.getUserRewardPointsPage(driver);
	}

	public UserMyProductReviewsPageObject openUserMyProductReviewsPage(WebDriver driver) {
		clickOnElement(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
		return PageGeneratorManager.getUserMyProductReviewsPage(driver);
	}

	public AdminConfigurationStoresPageObject openAdminConfigurationStoresPage(WebDriver driver) {
		sleepInSecond(1);
		clickOnElement(driver, BasePageUI.CONFIGURATION_MENU);
		sleepInSecond(1);
		clickOnElement(driver, BasePageUI.STORES_LINK);
		return PageGeneratorManager.getAdminConfigurationStoresPage(driver);
	}

	public AdminReportsSalesSummaryPageObject openAdminReportsSalesSummaryPage(WebDriver driver) {
		sleepInSecond(1);
		clickOnElement(driver, BasePageUI.REPORTS_MENU);
		sleepInSecond(1);
		clickOnElement(driver, BasePageUI.SALES_SUMMARY_LINK);
		return PageGeneratorManager.getAdminReportsSalesSummaryPage(driver);
	}

	// Level 08: Switch Role
	public UserHomePageObject clickLogoutLinkOnUserPage(WebDriver driver) {
		clickOnElement(driver, BasePageUI.LOGOUT_LINK_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public AdminLoginPageObject clickLogoutLinkOnAdminPage(WebDriver driver) {
		sleepInSecond(1);
		clickOnElement(driver, BasePageUI.LOGOUT_LINK_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}

	// Level 09: Dynamic Locator
	public BasePage openUserMyAccountPages_1(WebDriver driver, String pageName) {
		clickOnElement(driver, BasePageUI.DYNAMIC_MY_ACCOUNT_PAGES, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressesPage(driver);
		case "Orders":
			return PageGeneratorManager.getUserOrdersPage(driver);
		case "Downloadable products":
			return PageGeneratorManager.getUserDownloadableProductsPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointsPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewsPage(driver);
		default:
			throw new RuntimeException("Invalid page name in My Account area.");
		}
	}

	public void openUserMyAccountPages_2(WebDriver driver, String pageName) {
		clickOnElement(driver, BasePageUI.DYNAMIC_MY_ACCOUNT_PAGES, pageName);
	}
	
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;

}