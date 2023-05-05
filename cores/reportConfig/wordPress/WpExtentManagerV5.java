package reportConfig.wordPress;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import commons.wordPress.WpGlobalConstants;

public class WpExtentManagerV5 {
	public static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(WpGlobalConstants.EXTENT_REPORT_PATH + "WordPress_Demo.html");
		reporter.config().setReportName("WordPress HTML Report");
		reporter.config().setDocumentTitle("WordPress HTML Report");
		reporter.config().setTimelineEnabled(true);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTheme(Theme.STANDARD);

		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Company", "Automation FC");
		extentReports.setSystemInfo("Project", "WordPress");
		extentReports.setSystemInfo("Team", "Ngoc Quach");
		extentReports.setSystemInfo("JDK version", WpGlobalConstants.JAVA_VERSION);
		return extentReports;
	}
}
