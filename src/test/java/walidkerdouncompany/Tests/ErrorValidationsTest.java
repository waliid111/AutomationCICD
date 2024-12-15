package walidkerdouncompany.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import walidkerdouncompany.TestComponents.BaseTest;
import walidkerdouncompany.pageobjects.CartPage;
import walidkerdouncompany.pageobjects.CheckoutPage;
import walidkerdouncompany.pageobjects.ConfirmationPage;
import walidkerdouncompany.pageobjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = walidkerdouncompany.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		
		landingPage.loginApplication("testseleniumrhWrong@gmail.com", "Test@selenium1*");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		String countryName = "india";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("testseleniumrh@gmail.com", "Test@selenium1*");
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		
		Boolean match = cartPage.verifyProductDisplay(productName+"44");
		Assert.assertFalse(match); // Asertion and validation can't go on Page Object files
		
		
	}

}
