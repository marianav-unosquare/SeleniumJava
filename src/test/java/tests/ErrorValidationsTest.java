package tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException {
	
		lp.fillLoginForm("maana@email.com", "A0122078");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = lp.fillLoginForm("mariana@email.com", "A01220787m");
		//Add To cart
		productCatalogue.addProductToCart(productName);
		CartPage cartPage= productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay("ZaraCoat");
		Assert.assertFalse(match); //Validations should be inside the test case only, not written on the Page Object.
		
	}
	
}
