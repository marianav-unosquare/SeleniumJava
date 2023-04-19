package extentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReports {

	com.aventstack.extentreports.ExtentReports extent;

	@BeforeTest
	public void config() {
		// ExtentReports, extentSparkReporter
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		// Reporter makes configurations for report
		reporter.config().setReportName("WebAutomationResults");
		reporter.config().setDocumentTitle("Test Results");
		extent = new com.aventstack.extentreports.ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Mariana Vives");
	}

	@Test
	public void initialDemo() {
		ExtentTest test = extent.createTest("Initial demo");
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//main//java//drivers//chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		driver.close();
		test.fail("Result do not match");
		
		extent.flush();
	}

}
