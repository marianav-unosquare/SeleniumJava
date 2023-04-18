package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // Uses the driver to initialize elements used FindBy driver
	}

	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;

	@FindBy(css = ".totalRow button")
	WebElement checkoutElement;

	// Build Actions
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = productTitles.stream().anyMatch(i -> i.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage goToChechout() {
		checkoutElement.click();
		return new CheckoutPage(driver);
		
		
	}
}
