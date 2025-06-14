package reportConfig.nopCommerce;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import commons.nopCommerce.NcGlobalConstants;

public class NcExtentManagerV5 {
	public static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(NcGlobalConstants.EXTENT_REPORT_PATH + "NopCommerce.html");
		reporter.config().setReportName("NopCommerce HTML Report");
		reporter.config().setDocumentTitle("NopCommerce HTML Report");
		reporter.config().setTimelineEnabled(true);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTheme(Theme.STANDARD);

		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Company", "Automation FC");
		extentReports.setSystemInfo("Project", "NopCommerce");
		extentReports.setSystemInfo("Team", "Basus VN");
		extentReports.setSystemInfo("JDK version", NcGlobalConstants.JAVA_VERSION);
		return extentReports;
	}
}
