package tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class StandAloneTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
	@Test
	public void SubmitOrder() throws IOException, InterruptedException {
		
		
		ProductCatalogue productCatalogue = lp.fillLoginForm("mariana@email.com", "A01220787m");
		//Add To cart
		productCatalogue.addProductToCart(productName);
		CartPage cartPage= productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match); //Validations should be inside the test case only, not written on the Page Object.
		CheckoutPage checkoutPage = cartPage.goToChechout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMsg= confirmationPage.getConfirmationMessage();
		System.out.println(confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = lp.fillLoginForm("mariana@email.com", "A01220787m");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyProductDisplay(productName));
	}
	
}
