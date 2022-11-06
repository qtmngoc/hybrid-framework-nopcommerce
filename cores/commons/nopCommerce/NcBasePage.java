package commons.nopCommerce;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
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

import pageObjects.nopCommerce.NcAdminConfigurationStoresPO;
import pageObjects.nopCommerce.NcAdminLoginPO;
import pageObjects.nopCommerce.NcAdminReportsSalesSummaryPO;
import pageObjects.nopCommerce.NcUserAddressesPO;
import pageObjects.nopCommerce.NcUserCustomerInfoPO;
import pageObjects.nopCommerce.NcUserDownloadableProductsPO;
import pageObjects.nopCommerce.NcUserHomePO;
import pageObjects.nopCommerce.NcUserMyProductReviewsPO;
import pageObjects.nopCommerce.NcUserOrdersPO;
import pageObjects.nopCommerce.NcUserRewardPointsPO;
import pageUIs.nopCommerce.NcBasePUI;

public class NcBasePage {

	public static NcBasePage getBasePage() {
		return new NcBasePage();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected WebDriverWait setExplicitWait(WebDriver driver, long timeout) {
		return new WebDriverWait(driver, timeout);
	}
	
	protected void setImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
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
	
	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(2);
	}

	protected Alert waitForAlertPresent(WebDriver driver) {
		return setExplicitWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresent(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresent(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresent(driver).getText();
	}

	public void sendKeysToAlert(WebDriver driver, String value) {
		waitForAlertPresent(driver).sendKeys(value);
	}

	private Set<String> getAllWindowIDs(WebDriver driver) {
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

	private String getDynamicXpath(String dynamicXpath, String... dynamicValues) {
		if (dynamicXpath.startsWith("xpath=")) {
			dynamicXpath = String.format(dynamicXpath, (Object[]) dynamicValues);
		}
		return dynamicXpath;
	}

	protected WebElement getElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	protected List<WebElement> getListElements(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	protected void clickOnElement(WebDriver driver, String locatorType) {
		waitForElementClickable(driver, locatorType).click();
	}

	protected void clickOnElement(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		waitForElementClickable(driver, dynamicXpath, dynamicValues).click();
	}

	protected void sendKeysToElement(WebDriver driver, String locatorType, String value) {
		WebElement element = waitForElementVisible(driver, locatorType);
		element.clear();
		element.sendKeys(value);
	}

	protected void sendKeysToElement(WebDriver driver, String dynamicXpath, String value, String... dynamicValues) {
		WebElement element = waitForElementVisible(driver, dynamicXpath, dynamicValues);
		element.clear();
		element.sendKeys(value);
	}
	
	protected void clearElementValueByDeleteKey(WebDriver driver, String locatorType) {
		WebElement element = waitForElementVisible(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}
	
	protected void selectOptionFromDefaultDropdown(WebDriver driver, String locatorType, String textOption) {
		Select select = new Select(waitForElementClickable(driver, locatorType));
		select.selectByVisibleText(textOption);
	}

	protected void selectOptionFromDefaultDropdown(WebDriver driver, String dynamicXpath, String textOption, String... dynamicValues) {
		Select select = new Select(waitForElementClickable(driver, dynamicXpath, dynamicValues));
		select.selectByVisibleText(textOption);
	}

	protected String getSelectedOptionTextInDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(waitForElementVisible(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectedOptionTextInDefaultDropdown(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		Select select = new Select(waitForElementVisible(driver, dynamicXpath, dynamicValues));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isMultiSelectDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(waitForElementVisible(driver, locatorType));
		return select.isMultiple();
	}

	protected void selectOptionFromCustomDropdown(WebDriver driver, String parentLocatorType, String childLocatorType, String expectedTextOption) {
		clickOnElement(driver, parentLocatorType);
		sleepInSecond(1);
		List<WebElement> allOptions = setExplicitWait(driver, longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocatorType)));
		for (WebElement option : allOptions) {
			if (option.getText().trim().equals(expectedTextOption)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
				sleepInSecond(1);
				option.click();
				break;
			}
		}
	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return waitForElementVisible(driver, locatorType).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String dynamicXpath, String attributeName, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).getAttribute(attributeName);
	}

	protected String getElementText(WebDriver driver, String locatorType) {
		return waitForElementVisible(driver, locatorType).getText();
	}

	protected String getElementText(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).getText();
	}

	protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return waitForElementVisible(driver, locatorType).getCssValue(propertyName);
	}

	public String getHexColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getListElementsSize(WebDriver driver, String locatorType) {
		return getListElements(driver, locatorType).size();
	}

	protected int getListElementsSize(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return getListElements(driver, getDynamicXpath(dynamicXpath, dynamicValues)).size();
	}

	protected void checkCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = waitForElementClickable(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkCheckboxOrRadio(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		WebElement element = waitForElementClickable(driver, dynamicXpath, dynamicValues);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckCheckbox(WebDriver driver, String locatorType) {
		WebElement element = waitForElementClickable(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	protected void uncheckCheckbox(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		WebElement element = waitForElementClickable(driver, dynamicXpath, dynamicValues);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return waitForElementVisible(driver, locatorType).isDisplayed();
	}

	protected boolean isElementDisplayed(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).isDisplayed();
	}
	
	protected boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
		List<WebElement> elements = getListElements(driver, locatorType);
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
		
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isElementUndisplayed(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
		List<WebElement> elements = getListElements(driver, getDynamicXpath(dynamicXpath, dynamicValues));
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isElementEnabled(WebDriver driver, String locatorType) {
		return waitForElementVisible(driver, locatorType).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType) {
		return waitForElementVisible(driver, locatorType).isSelected();
	}
	
	protected boolean isElementSelected(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(waitForElementVisible(driver, locatorType));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void mouseHoverOnElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(waitForElementVisible(driver, locatorType)).perform();
	}

	protected void rightClickOnElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.contextClick(waitForElementClickable(driver, locatorType)).perform();
	}

	protected void doubleClickOnElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.doubleClick(waitForElementClickable(driver, locatorType)).perform();
	}

	protected void dragDropElement(WebDriver driver, String sourceLocatorType, String targetLocatorType) {
		Actions action = new Actions(driver);
		action.dragAndDrop(waitForElementVisible(driver, sourceLocatorType), waitForElementVisible(driver, targetLocatorType)).perform();
	}

	protected void pressKeyOnElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(waitForElementVisible(driver, locatorType), key).perform();
	}
	
	protected void pressKeyOnElement(WebDriver driver, String dynamicXpath, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(waitForElementVisible(driver, dynamicXpath, dynamicValues), key).perform();
	}

	protected void highlightElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickOnElementByJS(WebDriver driver, String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locatorType));
	}

	protected void clickOnElementByJS(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		waitForElementVisible(driver, dynamicXpath, dynamicValues);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, getDynamicXpath(dynamicXpath, dynamicValues)));
	}
	
	protected void scrollToElementByJS(WebDriver driver, String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locatorType));
	}

	protected void scrollToElementByJS(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, getDynamicXpath(dynamicXpath, dynamicValues)));
	}
	
	protected void scrollToBottomPageByJS(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	protected void scrollDownByJS(WebDriver driver, int pixel) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + pixel + ")");
	}

	protected void removeAttributeInDOMByJS(WebDriver driver, String locatorType, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locatorType));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
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

		return setExplicitWait(driver, longTimeout).until(jQueryLoad) && setExplicitWait(driver, longTimeout).until(jsLoad);
	}

	protected String getElementValidationMessageByJS(WebDriver driver, String locatorType) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locatorType));
	}
	
	protected boolean isImageLoadedByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isImageLoadedByJS(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, getDynamicXpath(dynamicXpath, dynamicValues)));
		return status;
	}

	protected WebElement getShadowDOMByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;", getElement(driver, locatorType));
		return element;
	}
	
	protected String getElementValueByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath= ", "");
		return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
	}
	
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = NcGlobalConstants.UPLOAD_FILES;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getElement(driver, NcBasePUI.UPLOAD_FILE).sendKeys(fullFileName);
	}
	
	protected WebElement waitForElementVisible(WebDriver driver, String locatorType) {
		return setExplicitWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected WebElement waitForElementVisible(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return setExplicitWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(dynamicXpath, dynamicValues))));
	}
	
	protected void waitForElementVisible(WebDriver driver, long timeout, String dynamicXpath, String... dynamicValues) {
		setExplicitWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(dynamicXpath, dynamicValues))));
	}

	protected void waitForAllElementsVisible(WebDriver driver, String locatorType) {
		setExplicitWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	protected void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
		setExplicitWait(driver, shortTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorType) {
		setExplicitWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementInvisible(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		setExplicitWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(dynamicXpath, dynamicValues))));
	}

	protected void waitForAllElementsInvisible(WebDriver driver, String locatorType) {
		setExplicitWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, locatorType)));
	}

	protected WebElement waitForElementClickable(WebDriver driver, String locatorType) {
		return setExplicitWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	protected WebElement waitForElementClickable(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return setExplicitWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(dynamicXpath, dynamicValues))));
	}

	// -----------------------------------------------------------------------------------------------------------------
	// Level 07: Switch Page
	public NcUserCustomerInfoPO openUserCustomerInfoPage(WebDriver driver) {
		clickOnElement(driver, NcBasePUI.CUSTOMER_INFO_LINK);
		return NcPageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public NcUserAddressesPO openUserAddressesPage(WebDriver driver) {
		clickOnElement(driver, NcBasePUI.ADDRESSES_LINK);
		return NcPageGeneratorManager.getUserAddressesPage(driver);
	}

	public NcUserOrdersPO openUserOrdersPage(WebDriver driver) {
		clickOnElement(driver, NcBasePUI.ORDERS_LINK);
		return NcPageGeneratorManager.getUserOrdersPage(driver);
	}

	public NcUserDownloadableProductsPO openUserDownloadableProductsPage(WebDriver driver) {
		clickOnElement(driver, NcBasePUI.DOWNLOADABLE_PRODUCTS_LINK);
		return NcPageGeneratorManager.getUserDownloadableProductsPage(driver);
	}

	public NcUserRewardPointsPO openUserRewardPointsPage(WebDriver driver) {
		clickOnElement(driver, NcBasePUI.REWARD_POINTS_LINK);
		return NcPageGeneratorManager.getUserRewardPointsPage(driver);
	}

	public NcUserMyProductReviewsPO openUserMyProductReviewsPage(WebDriver driver) {
		clickOnElement(driver, NcBasePUI.MY_PRODUCT_REVIEWS_LINK);
		return NcPageGeneratorManager.getUserMyProductReviewsPage(driver);
	}

	public NcAdminConfigurationStoresPO openAdminConfigurationStoresPage(WebDriver driver) {
		sleepInSecond(1);
		clickOnElement(driver, NcBasePUI.CONFIGURATION_MENU);
		sleepInSecond(1);
		clickOnElement(driver, NcBasePUI.STORES_LINK);
		return NcPageGeneratorManager.getAdminConfigurationStoresPage(driver);
	}

	public NcAdminReportsSalesSummaryPO openAdminReportsSalesSummaryPage(WebDriver driver) {
		sleepInSecond(1);
		clickOnElement(driver, NcBasePUI.REPORTS_MENU);
		sleepInSecond(1);
		clickOnElement(driver, NcBasePUI.SALES_SUMMARY_LINK);
		return NcPageGeneratorManager.getAdminReportsSalesSummaryPage(driver);
	}

	// Level 08: Switch Role
	public NcUserHomePO clickLogoutLinkOnUserPage(WebDriver driver) {
		clickOnElement(driver, NcBasePUI.LOGOUT_LINK_USER);
		return NcPageGeneratorManager.getUserHomePage(driver);
	}

	public NcAdminLoginPO clickLogoutLinkOnAdminPage(WebDriver driver) {
		sleepInSecond(1);
		clickOnElement(driver, NcBasePUI.LOGOUT_LINK_ADMIN);
		return NcPageGeneratorManager.getAdminLoginPage(driver);
	}

	// Level 09: Dynamic Locator
	public NcBasePage openUserMyAccountPages_1(WebDriver driver, String pageName) {
		clickOnElement(driver, NcBasePUI.DYNAMIC_MY_ACCOUNT_PAGES, pageName);
		switch (pageName) {
		case "Customer info":
			return NcPageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			return NcPageGeneratorManager.getUserAddressesPage(driver);
		case "Orders":
			return NcPageGeneratorManager.getUserOrdersPage(driver);
		case "Downloadable products":
			return NcPageGeneratorManager.getUserDownloadableProductsPage(driver);
		case "Reward points":
			return NcPageGeneratorManager.getUserRewardPointsPage(driver);
		case "My product reviews":
			return NcPageGeneratorManager.getUserMyProductReviewsPage(driver);
		default:
			throw new RuntimeException("Invalid page name in My Account area.");
		}
	}

	public void openUserMyAccountPages_2(WebDriver driver, String pageName) {
		clickOnElement(driver, NcBasePUI.DYNAMIC_MY_ACCOUNT_PAGES, pageName);
	}
	
	// Level 19: Pattern Object
	public void clickOnLinkByText(WebDriver driver, String linkText) {
		clickOnElement(driver, NcBasePUI.DYNAMIC_LINK_BY_TEXT, linkText);
	}
	
	public void selectRadioButtonByLabelText(WebDriver driver, String labelText) {
		checkCheckboxOrRadio(driver, NcBasePUI.DYNAMIC_RADIO_BY_LABEL_TEXT, labelText);
	}
	
	public void inputIntoTextboxById(WebDriver driver, String idAttribute, String value) {
		sendKeysToElement(driver, NcBasePUI.DYNAMIC_TEXTBOX_BY_ID, value, idAttribute);
	}
	
	public void selectDropdownByName(WebDriver driver, String nameAttribute, String value) {
		selectOptionFromDefaultDropdown(driver, NcBasePUI.DYNAMIC_DROPDOWN_BY_NAME, value, nameAttribute);
	}
	
	public void selectCheckboxById(WebDriver driver, String idAttribute) {
		checkCheckboxOrRadio(driver, NcBasePUI.DYNAMIC_CHECKBOX_BY_ID, idAttribute);
	}
	
	public void clickOnButtonByText(WebDriver driver, String buttonText) {
		clickOnElement(driver, NcBasePUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}
	
	public boolean isRadioButtonSelectedByLabelText(WebDriver driver, String labelText) {
		return isElementSelected(driver, NcBasePUI.DYNAMIC_RADIO_BY_LABEL_TEXT, labelText);
	}
	
	public String getTextboxValueById(WebDriver driver, String idAttribute) {
		return getElementAttribute(driver, NcBasePUI.DYNAMIC_TEXTBOX_BY_ID, "value", idAttribute);
	}
	
	public String getDropdownValueByName(WebDriver driver, String nameAttribute) {
		return getElementAttribute(driver, NcBasePUI.DYNAMIC_DROPDOWN_BY_NAME, "value", nameAttribute);
	}
	
	public boolean isCheckboxSelectedById(WebDriver driver, String idAttribute) {
		return isElementSelected(driver, NcBasePUI.DYNAMIC_CHECKBOX_BY_ID, idAttribute);
	}

	private long longTimeout = NcGlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = NcGlobalConstants.SHORT_TIMEOUT;
}