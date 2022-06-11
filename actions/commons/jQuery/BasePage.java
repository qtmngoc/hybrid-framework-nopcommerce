package commons.jQuery;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

import pageUIs.jQuery.uploadFiles.BasePageUI;

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

	private WebDriverWait explicitWait(WebDriver driver, long timeout) {
		return new WebDriverWait(driver, timeout);
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

	public Alert waitForAlertPresent(WebDriver driver) {
		return explicitWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
	}

	public void accepAlert(WebDriver driver) {
		waitForAlertPresent(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresent(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresent(driver).getText();
	}

	public void sendKeysToAlert(WebDriver driver, String textValue) {
		waitForAlertPresent(driver).sendKeys(textValue);
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

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	private String getDynamicXpath(String dynamicXpath, String... dynamicValues) {
		return String.format(dynamicXpath, (Object[]) dynamicValues);
	}

	public WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}

	public List<WebElement> getListElements(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}

	public void clickOnElement(WebDriver driver, String xpathLocator) {
		waitForElementClickable(driver, xpathLocator).click();
	}

	public void clickOnElement(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		waitForElementClickable(driver, dynamicXpath, dynamicValues).click();
	}

	public void sendKeysToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = waitForElementVisible(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendKeysToElement(WebDriver driver, String dynamicXpath, String textValue, String... dynamicValues) {
		WebElement element = waitForElementVisible(driver, dynamicXpath, dynamicValues);
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectOptionInDefaultDropdown(WebDriver driver, String xpathLocator, String textOption) {
		Select select = new Select(waitForElementClickable(driver, xpathLocator));
		select.selectByVisibleText(textOption);
	}

	public void selectOptionInDefaultDropdown(WebDriver driver, String dynamicXpath, String textOption, String... dynamicValues) {
		Select select = new Select(waitForElementClickable(driver, dynamicXpath, dynamicValues));
		select.selectByVisibleText(textOption);
	}

	public String getSelectedOptionTextInDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(waitForElementVisible(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	public String getSelectedOptionTextInDefaultDropdown(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		Select select = new Select(waitForElementVisible(driver, dynamicXpath, dynamicValues));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isMultiSelectDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(waitForElementVisible(driver, xpathLocator));
		return select.isMultiple();
	}

	public void selectOptionInCustomDropdown(WebDriver driver, String parentXpathLocator, String childXpathLocator, String expectedTextOption) {
		clickOnElement(driver, parentXpathLocator);
		sleepInSecond(1);
		List<WebElement> allOptions = explicitWait(driver, longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpathLocator)));
		for (WebElement option : allOptions) {
			if (option.getText().trim().equals(expectedTextOption)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
				sleepInSecond(1);
				option.click();
				break;
			}
		}
	}

	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return waitForElementVisible(driver, xpathLocator).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String dynamicXpath, String attributeName, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String xpathLocator) {
		return waitForElementVisible(driver, xpathLocator).getText();
	}

	public String getElementText(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).getText();
	}

	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return waitForElementVisible(driver, xpathLocator).getCssValue(propertyName);
	}

	public String getHexColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getListElementsSize(WebDriver driver, String xpathLocator) {
		return getListElements(driver, xpathLocator).size();
	}

	public int getListElementsSize(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return getListElements(driver, getDynamicXpath(dynamicXpath, dynamicValues)).size();
	}
	
	public void checkCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		WebElement element = waitForElementClickable(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkCheckboxOrRadio(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		WebElement element = waitForElementClickable(driver, dynamicXpath, dynamicValues);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = waitForElementClickable(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckCheckbox(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		WebElement element = waitForElementClickable(driver, dynamicXpath, dynamicValues);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return waitForElementVisible(driver, xpathLocator).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return waitForElementVisible(driver, dynamicXpath, dynamicValues).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
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
	
	public boolean isElementUndisplayed(WebDriver driver, String dynamicXpath, String... dynamicValues) {
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
	
	public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getElement(driver, xpathLocator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return waitForElementVisible(driver, xpathLocator).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(waitForElementVisible(driver, xpathLocator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void mouseHoverOnElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(waitForElementVisible(driver, xpathLocator)).perform();
	}

	public void rightClickOnElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.contextClick(waitForElementClickable(driver, xpathLocator)).perform();
	}

	public void doubleClickOnElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.doubleClick(waitForElementClickable(driver, xpathLocator)).perform();
	}

	public void dragDropElement(WebDriver driver, String sourceXpathLocator, String targetXpathLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(waitForElementVisible(driver, sourceXpathLocator), waitForElementVisible(driver, targetXpathLocator)).perform();
	}

	public void sendKeypressToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(waitForElementVisible(driver, xpathLocator), key).perform();
	}
	
	public void sendKeypressToElement(WebDriver driver, String dynamicXpath, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(waitForElementVisible(driver, dynamicXpath, dynamicValues), key).perform();
	}

	public void scrollToBottomPageByJS(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickOnElementByJS(WebDriver driver, String xpathLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, xpathLocator));
	}

	public void scrollToElementByJS(WebDriver driver, String xpathLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, xpathLocator));
	}

	public void removeAttributeInDOMByJS(WebDriver driver, String xpathLocator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, xpathLocator));
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

		return explicitWait(driver, longTimeout).until(jQueryLoad) && explicitWait(driver, longTimeout).until(jsLoad);
	}

	public String getElementValidationMessageByJS(WebDriver driver, String xpathLocator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getElement(driver, xpathLocator));
	}
	
	public boolean isImageLoadedByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isImageLoadedByJS(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, getDynamicXpath(dynamicXpath, dynamicValues)));
		return status;
	}

	public WebElement getShadowDOMByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot;", getElement(driver, xpathLocator));
		return element;
	}
	
	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILES;
		
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(fullFileName);
	}

	public WebElement waitForElementVisible(WebDriver driver, String xpathLocator) {
		return explicitWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	public WebElement waitForElementVisible(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return explicitWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicXpath(dynamicXpath, dynamicValues))));
	}

	public void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		explicitWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
	
	public void waitForElementUndisplayed(WebDriver driver, String xpathLocator) {
		driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
		explicitWait(driver, shortTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
	}

	public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		explicitWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	public void waitForElementInvisible(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		explicitWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicXpath(dynamicXpath, dynamicValues))));
	}

	public void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
		explicitWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, xpathLocator)));
	}

	public WebElement waitForElementClickable(WebDriver driver, String xpathLocator) {
		return explicitWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}

	public WebElement waitForElementClickable(WebDriver driver, String dynamicXpath, String... dynamicValues) {
		return explicitWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicXpath(dynamicXpath, dynamicValues))));
	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

}