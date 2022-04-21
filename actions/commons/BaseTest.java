package commons;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String separatorChar = File.separator;

	protected WebDriver getBrowserDriver(String browserName) {
		if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + separatorChar + "browserDrivers" + separatorChar + "geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + separatorChar + "browserDrivers" + separatorChar + "chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("Edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + separatorChar + "browserDrivers" + separatorChar + "msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Invalid browser name");
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
}