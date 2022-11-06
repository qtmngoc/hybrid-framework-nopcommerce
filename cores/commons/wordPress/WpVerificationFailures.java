package commons.wordPress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.ITestResult;

public class WpVerificationFailures extends HashMap<ITestResult, List<Throwable>> {
	private WpVerificationFailures() {
		super();
	}

	public static WpVerificationFailures getFailures() {
		if (failures == null) {
			failures = new WpVerificationFailures();
		}
		return failures;
	}

	public List<Throwable> getFailuresForTest(ITestResult result) {
		List<Throwable> exceptions = get(result);
		return exceptions == null ? new ArrayList<Throwable>() : exceptions;
	}

	public void addFailureForTest(ITestResult result, Throwable throwable) {
		List<Throwable> exceptions = getFailuresForTest(result);
		exceptions.add(throwable);
		put(result, exceptions);
	}

	private static final long serialVersionUID = 1L;
	private static WpVerificationFailures failures;
}