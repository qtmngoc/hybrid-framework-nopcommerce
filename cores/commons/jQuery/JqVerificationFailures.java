package commons.jQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.ITestResult;

public class JqVerificationFailures extends HashMap<ITestResult, List<Throwable>> {
	private JqVerificationFailures() {
		super();
	}

	public static JqVerificationFailures getFailures() {
		if (failures == null) {
			failures = new JqVerificationFailures();
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
	private static JqVerificationFailures failures;
}