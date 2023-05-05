package commons.nopCommerce;

import java.io.File;

public class NcGlobalConstants {
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");

	public static final String UPLOAD_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_PATH = PROJECT_PATH + File.separator + "downloadFiles" + File.separator;
	
	public static final String BROWSER_LOG_PATH = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
	public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + File.separator + "browserExtensions" + File.separator;
		
	public static final String REPORTNG_SCREENSHOT_PATH = PROJECT_PATH + File.separator + "reportNGScreenshots" + File.separator;
	public static final String EXTENT_REPORT_PATH = PROJECT_PATH + File.separator + "extentReportV5" + File.separator;
	public static final String ALLURE_RESULT_PATH = PROJECT_PATH + File.separator + "allureResults" + File.separator;
	
	public static final String USER_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";

	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 20;
	
	public static final int RETRY_CASE_FAILED = 3;
}
