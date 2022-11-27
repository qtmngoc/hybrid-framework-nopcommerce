package commons.wordPress;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
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

import pageObjects.wordPress.WpAdminDashboardPO;
import pageObjects.wordPress.WpUserHomePO;
import pageUIs.wordPress.WpBasePUI;

public class WpBasePage {

	public static WpBasePage getBasePage() {
		return new WpBasePage();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return String.valueOf(month);
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return String.valueOf(now.getYear());
	}

	public String getCurrentDate() {
		return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();
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

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	private String getDynamicXpath(String dynamicXpath, String... dynamicValues) {
		return String.format(dynamicXpath, (Object[]) dynamicValues);
	}

	protected WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}

	protected List<WebElement> getListElements(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}

	protected void clickOnElement(WebDriver driver, String xpathLocator) {
		waitForElementClickable(driver, xpathLocator).click();
	}

	protected void clickOnElement(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		waitForElementClickable(driver, dynamicXpath, dynamicValues).click();
	}

	protected void sendKeysToElement(WebDriver driver, String xpathLocator, String value) {
		WebElement element = waitForElementVisible(driver, xpathLocator);
		element.clear();
		element.sendKeys(value);
	}

	protected void sendKeysToElement(WebDriver driver, String dynamicXpath, String value, String... dynamicValues) {
		WebElement element = waitForElementVisible(driver, dynamicXpath, dynamicValues);
		element.clear();
		element.sendKeys(value);
	}
	
	protected void clearElementValueByDeleteKey(WebDriver driver, String xpathLocator) {
		WebElement element = waitForElementVisible(driver, xpathLocator);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	protected void selectOptionFromDefaultDropdown(WebDriver driver, String xpathLocator, String textOption) {
		Select select = new Select(waitForElementClickable(driver, xpathLocator));
		select.selectByVisibleText(textOption);
	}

	protected void selectOptionFromDefaultDropdown(WebDriver driver, String dynamicXpath, String textOption, String... dynamicValues) {
		Select select = new Select(waitForElementClickable(driver, dynamicXpath, dynamicValues));
		select.selectByVisibleText(textOption);
	}

	protected String getSelectedOptionTextInDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(waitForElementVisible(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectedOptionTextInDefaultDropdown(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		Select select = new Select(waitForElementVisible(driver, dynamicXpath, dynamicValues));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isMultiSelectDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(waitForElementVisible(driver, xpathLocator));
		return select.isMultiple();
	}

	protected void selectOptionFromCustomDropdown(WebDriver driver, String parentXpathLocator, String childXpathLocator, String expectedTextOption) {
		clickOnElement(driver, parentXpathLocator);
		sleepInSecond(1);
		List<WebElement> allOptions = setExplicitWait(driver, longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpathLocator)));
		for (WebElement option : allOptions) {
			if (option.getText().trim().equals(expectedTextOption)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
				sleepInSecond(1);
				option.click();
				break;
			}
		}
	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return waitForElementVisible(driver, xpathLocator).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String dynamicXpath, String attributeName, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).getAttribute(attributeName);
	}

	protected String getElementText(WebDriver driver, String xpathLocator) {
		return waitForElementVisible(driver, xpathLocator).getText();
	}

	protected String getElementText(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).getText();
	}

	protected String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return waitForElementVisible(driver, xpathLocator).getCssValue(propertyName);
	}

	public String getHexColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getListElementsSize(WebDriver driver, String xpathLocator) {
		return getListElements(driver, xpathLocator).size();
	}

	protected int getListElementsSize(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return getListElements(driver, getDynamicXpath(dynamicXpath, dynamicValues)).size();
	}
	
	protected void checkCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		WebElement element = waitForElementClickable(driver, xpathLocator);
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

	protected void uncheckCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = waitForElementClickable(driver, xpathLocator);
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

	protected boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return waitForElementVisible(driver, xpathLocator).isDisplayed();
	}

	protected boolean isElementDisplayed(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).isDisplayed();
	}

	protected boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
		List<WebElement> elements = getListElements(driver, xpathLocator);
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
	
	protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return waitForElementVisible(driver, xpathLocator).isSelected();
	}
	
	protected boolean isElementSelected(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(waitForElementVisible(driver, xpathLocator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void mouseHoverOnElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(waitForElementVisible(driver, xpathLocator)).perform();
	}

	protected void rightClickOnElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.contextClick(waitForElementClickable(driver, xpathLocator)).perform();
	}

	protected void doubleClickOnElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.doubleClick(waitForElementClickable(driver, xpathLocator)).perform();
	}

	protected void dragDropElement(WebDriver driver, String sourceXpathLocator, String targetXpathLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(waitForElementVisible(driver, sourceXpathLocator), waitForElementVisible(driver, targetXpathLocator)).perform();
	}

	protected void pressKeyOnElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(waitForElementVisible(driver, xpathLocator), key).perform();
	}
	
	protected void pressKeyOnElement(WebDriver driver, String dynamicXpath, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(waitForElementVisible(driver, dynamicXpath, dynamicValues), key).perform();
	}

	protected void highlightElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	protected void clickOnElementByJS(WebDriver driver, String xpathLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, xpathLocator));
	}

	protected void clickOnElementByJS(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		waitForElementVisible(driver, dynamicXpath, dynamicValues);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, getDynamicXpath(dynamicXpath, dynamicValues)));
	}
	
	protected void scrollToElementByJS(WebDriver driver, String xpathLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, xpathLocator));
	}

	protected void scrollToElementByJS(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, getDynamicXpath(dynamicXpath, dynamicValues)));
	}
	
	protected void scrollToBottomPageByJS(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	protected void scrollToTopPageByJS(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	
	protected void scrollDownByJS(WebDriver driver, int pixel) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, " + pixel + ")");
	}

	protected void removeAttributeInDOMByJS(WebDriver driver, String xpathLocator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, xpathLocator));
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

	protected String getElementValidationMessageByJS(WebDriver driver, String xpathLocator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, xpathLocator));
	}
	
	protected boolean isImageLoadedByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, xpathLocator));
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

	protected WebElement getShadowDOMByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;", getElement(driver, xpathLocator));
		return element;
	}
	
	protected String getElementValueByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
	}
	
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = WpGlobalConstants.UPLOAD_FILES;		
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getElement(driver, WpBasePUI.UPLOAD_FILE).sendKeys(fullFileName);
	}

	protected WebElement waitForElementVisible(WebDriver driver, String xpathLocator) {
		return setExplicitWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	protected WebElement waitForElementVisible(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return setExplicitWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicXpath(dynamicXpath, dynamicValues))));
	}
	
	protected void waitForElementVisible(WebDriver driver, long timeout, String dynamicXpath, String... dynamicValues) {
		setExplicitWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicXpath(dynamicXpath, dynamicValues))));
	}
	
	protected void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		setExplicitWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
	
	protected void waitForElementUndisplayed(WebDriver driver, String xpathLocator) {
		driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
		setExplicitWait(driver, shortTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
	}

	protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		setExplicitWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	protected void waitForElementInvisible(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		setExplicitWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicXpath(dynamicXpath, dynamicValues))));
	}

	protected void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
		setExplicitWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, xpathLocator)));
	}

	protected WebElement waitForElementClickable(WebDriver driver, String xpathLocator) {
		return setExplicitWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}

	protected WebElement waitForElementClickable(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return setExplicitWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicXpath(dynamicXpath, dynamicValues))));
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------------------
	public WpAdminDashboardPO loginToSystem(WebDriver driver, String username, String password) {
		sendKeysToElement(driver, WpBasePUI.USERNAME_TEXTBOX, username);
		clickOnElement(driver, WpBasePUI.CONTINUE_BUTTON);
		sendKeysToElement(driver, WpBasePUI.PASSWORD_TEXTBOX, password);
		clickOnElement(driver, WpBasePUI.LOGIN_BUTTON);
		return WpPageGeneratorManager.getAdminDashboardPage(driver);
	}
	
	public WpAdminDashboardPO openAdminSite(WebDriver driver, String adminUrl) {
		openPageUrl(driver, adminUrl);
		return WpPageGeneratorManager.getAdminDashboardPage(driver);
	}
	
	public WpUserHomePO openUserSite(WebDriver driver, String userUrl) {
		openPageUrl(driver, userUrl);
		return WpPageGeneratorManager.getUserHomePage(driver);
	}

	private long longTimeout = WpGlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = WpGlobalConstants.SHORT_TIMEOUT;
}