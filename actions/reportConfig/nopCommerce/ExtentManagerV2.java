/* package reportConfig.nopCommerce;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManagerV2 {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			extent = new ExtentReports(System.getProperty("user.dir") + "/extentReportV2/ExtentReportScreenshot.html", true);
		}
		return extent;
	}
} */