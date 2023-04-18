package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends CartPage {

	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this); //Uses the driver to initialize elements used FindBy driver
	}
	
	@FindBy (css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(i -> i.getText().equalsIgnoreCase(productName));
		return match;
	}

}
