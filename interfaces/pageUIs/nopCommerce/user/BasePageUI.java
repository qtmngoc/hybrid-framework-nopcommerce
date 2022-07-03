package pageUIs.nopCommerce.user;

public class BasePageUI {
	
	public static final String CUSTOMER_INFO_LINK = "xpath= //li[@class='customer-info inactive']/a";
	public static final String ADDRESSES_LINK = "xpath= //li[@class='customer-addresses inactive']/a";
	public static final String ORDERS_LINK = "xpath= //li[@class='customer-orders inactive']/a";
	public static final String DOWNLOADABLE_PRODUCTS_LINK = "xpath= //li[@class='downloadable-products inactive']/a";
	public static final String REWARD_POINTS_LINK = "xpath= //li[@class='reward-points inactive']/a";
	public static final String MY_PRODUCT_REVIEWS_LINK = "xpath= //li[@class='customer-reviews inactive']/a";
	
	public static final String DYNAMIC_MY_ACCOUNT_PAGES = "xpath= //div[contains(@class,'account-navigation')]//a[text()='%s']";
	
	public static final String LOGOUT_LINK_USER = "xpath= //a[@class='ico-logout']";
	public static final String LOGOUT_LINK_ADMIN = "xpath= //a[text()='Logout']";
	
	public static final String CONFIGURATION_MENU = "xpath= //p[contains(text(),'Configuration')]/i";
	public static final String REPORTS_MENU = "xpath= //p[contains(text(),'Reports')]/i";
	
	public static final String STORES_LINK = "xpath= //p[contains(text(),'Stores')]";
	public static final String SALES_SUMMARY_LINK = "xpath= //p[contains(text(),'Sales summary')]";
	
	public static final String UPLOAD_FILE = "xpath= //input[@type='file']";
	
	// Pattern Object
	public static final String DYNAMIC_LINK_BY_TEXT = "xpath= //a[text()='%s']";
	public static final String DYNAMIC_RADIO_BY_LABEL_TEXT = "xpath= //label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath= //input[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath= //select[@name='%s']";
	public static final String DYNAMIC_CHECKBOX_BY_ID = "xpath= //input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath= //button[text()='%s']";
	
}
