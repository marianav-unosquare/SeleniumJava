package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lp;

	public WebDriver initializeDriver() throws IOException {
		
		//Properties class
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//main//java//drivers//chromedriver.exe");
			driver = new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	//JsonReader
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//String to Hashmap with dependency jackson databind 
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
		//List of {map}, {map}
	}

	
	//akwaysRun=true allows our groups to successfuly work 
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}

	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
