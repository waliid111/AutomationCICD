package walidkerdouncompany.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import walidkerdouncompany.TestComponents.BaseTest;
import walidkerdouncompany.pageobjects.CartPage;
import walidkerdouncompany.pageobjects.CheckoutPage;
import walidkerdouncompany.pageobjects.ConfirmationPage;
import walidkerdouncompany.pageobjects.LandingPage;
import walidkerdouncompany.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	public String countryName = "india";
	
	@Given("I Landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCart();
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match); // Asertion and validation can't go on Page Object files
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		
		tearDown();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string)
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
		
		tearDown();
	}

}
