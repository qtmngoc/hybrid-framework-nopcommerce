package pageUIs.wordPress;

public class WpAdminCategoryPUI {

	public static final String ADD_NEW_CATEGORY_BUTTON = "//button[text()='Add New Category']";
	public static final String DIALOG_ADD_OR_UPDATE_OR_OK_BUTTON = "//div[@class='dialog__action-buttons']//button[@class='button is-primary']";
	public static final String CLOSE_ICON = "//div[@aria-label='Close Search']/*";
	public static final String CATEGORY_ITEM = "//span[text()='%s']";
	public static final String ELLIPSIS_MENU_ITEM = "//div[@class='popover__menu']//button[text()='%s']";
	public static final String ELLIPSIS_MENU = "//span[text()='%s']/parent::span/following-sibling::span[@class='ellipsis-menu']/button";
	public static final String DIALOG_CONFIRM_DELETE_MESSAGE = "//div[@class='dialog__content']/p[contains(text(), '%s')]";
	public static final String DIALOG_PARENT_MESSAGE = "//span[text()='Disable to select a Parent Category']";
	public static final String NO_RESULTS_MESSAGE = "//div[text()='No results found.']";
	public static final String DIALOG_CATEGORY_RADIO = "//div[@class='dialog__content']//span[text()='%s']/preceding-sibling::input";
	public static final String DIALOG_DESCRIPTION_TEXTAREA = "//legend[text()='Description']/following-sibling::textarea";
	public static final String DIALOG_NAME_TEXTBOX = "//input[@placeholder='New Category Name']";
	public static final String DIALOG_SEARCH_TEXTBOX = "//div[@class='dialog__content']//input[@type='search']";
	public static final String SEARCH_TEXTBOX = "//div[@class='search__input-fade']/input";
	public static final String DIALOG_PARENT_TOGGLE = "//input[@class='components-form-toggle__input']";
	
}

