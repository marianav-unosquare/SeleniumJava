package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class StandAloneTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		
		ProductCatalogue productCatalogue = lp.fillLoginForm(input.get("email"), input.get("password"));
		//Add To cart
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage= productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("product"));
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
	
	//Extent Reports --
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") +"//src//test//java//data//purchaseOrder.json");
//		HashMap<String,String> map = new HashMap<String, String>();
//		map.put("email", "mariana@email.com");
//		map.put("password", "A01220787m");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map2 = new HashMap<String, String>();
//		map2.put("email", "mariana@gmail.com");
//		map2.put("password", "Mariana123.");
//		map2.put("product", "ADIDAS ORIGINAL");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
}
