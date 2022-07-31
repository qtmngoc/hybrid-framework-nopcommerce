package pageUIs.wordPress;

public class AdminPostAllPUI {

	public static final String ADD_NEW_BUTTON = "//span[@id='split-page-title-action']//a[text()='Add New']";
	public static final String APPLY_BUTTON = "//input[@id='doaction']";
	public static final String SEARCH_POSTS_BUTTON = "//input[@id='search-submit']";
	public static final String ROW_CHECKBOX_BY_POST_TITLE = "//label[contains(text(),'%s')]//following-sibling::input";
	public static final String BULK_ACTIONS_DROPDOWN = "//select[@id='bulk-action-selector-top']";
	public static final String MOVE_TO_TRASH_MESSAGE = "//div[@id='message']/p[contains(text(),'%s')]";
	public static final String POST_INFO_TEXT_BY_COLUMN_NAME = "//table//td[@data-colname='%s']//a[text()='%s']";
	public static final String SEARCH_TEXTBOX = "//input[@id='post-search-input']";
	public static final String SEARCH_RESULTS_TEXT_BY_POST_TITLE = "//span[@class='subtitle']/strong[text()='%s']";
	
}
