package tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import testComponents.BaseTest;

public class DemoTest extends BaseTest{
	
	@Test
	public void SubmitOrder() throws IOException, InterruptedException {
	
		lp.fillLoginForm("maana@email.com", "A0122078");
		Assert.assertEquals("Incorrect email or password", lp.getErrorMessage());
	
	}
}
