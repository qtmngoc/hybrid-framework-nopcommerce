package commons.liveGuru;

import java.io.File;

public class LgGlobalConstants {
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");

	public static final String UPLOAD_FILES = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FILES = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;
	
	public static final String USER_PAGE_URL = "http://live.techpanda.org/";
	public static final String ADMIN_PAGE_URL = "http://live.techpanda.org/index.php/backendlogin";
	
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 20;
	
}
