package pageUIs.liveGuru.admin;

public class AdminCustomersPageUI {

	public static final String MESSAGE_POPUP = "//div[@id='message-popup-window']";
	public static final String CLOSE_MESSAGE_POPUP = "//div[@id='message-popup-window']//a[@title='close']";
	public static final String ACTIONS_DROPDOWN = "//label[text()='Actions']/following-sibling::select";
	public static final String LOGOUT_LINK = "//a[@class='link-logout']";
	
	public static final String COLUMN_INDEX_BY_NAME = "//span[text()='%s']//ancestor::th/preceding-sibling::th";
	public static final String FILTER_TEXTBOX_BY_COLUMN_NAME = "//tr[@class='filter']/th[%s]//input";
	public static final String TEXT_CELL_BY_ROW_AND_COLUMN_INDEX = "//div[@class='grid']//tbody/tr[%s]/td[%s]";
	public static final String CHECKBOX_CELL_BY_ROW_INDEX = "//div[@class='grid']//tbody/tr[%s]//input[@type='checkbox']";
	public static final String BUTTON_BY_NAME = "//button[@title='%s']";
	
}
