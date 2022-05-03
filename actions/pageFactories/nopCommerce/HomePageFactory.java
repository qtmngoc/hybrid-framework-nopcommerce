package pageFactories.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.nopCommerce.BasePageFactory;

public class HomePageFactory extends BasePageFactory {
	private WebDriver driver;

	public HomePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Page UI
	@FindBy(how= How.XPATH, using = "//a[@class='ico-register']")
	private WebElement registerLink;
	
	@FindBy(how= How.CSS, using = "a.ico-login")
	private WebElement loginLink;
	
	@FindBy(xpath = "//a[@class='ico-account']")
	private WebElement myAccountLink;
	
	@FindBy(css = "a.ico-logout")
	private WebElement logoutLink;
	
	// Page Object/ Action
	public void clickOnRegisterLink() {
		waitElementClickable(driver, registerLink);
		clickOnElement(driver, registerLink);
	}

	public void clickOnLoginLink() {
		waitElementClickable(driver, loginLink);
		clickOnElement(driver, loginLink);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitElementVisible(driver, myAccountLink);
		return isElementDisplayed(driver, myAccountLink);
	}

	public boolean isLogoutLinkDisplayed() {
		waitElementVisible(driver, logoutLink);
		return isElementDisplayed(driver, logoutLink);
	}
}
