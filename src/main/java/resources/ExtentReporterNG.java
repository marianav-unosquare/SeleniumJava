package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		// Reporter makes configurations for report
		reporter.config().setReportName("WebAutomationResults");
		reporter.config().setDocumentTitle("Test Results");
		com.aventstack.extentreports.ExtentReports extent = new com.aventstack.extentreports.ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Mariana Vives");
	return extent;
	}
	
}
