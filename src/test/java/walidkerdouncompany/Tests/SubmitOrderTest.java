package walidkerdouncompany.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import walidkerdouncompany.TestComponents.BaseTest;
import walidkerdouncompany.pageobjects.CartPage;
import walidkerdouncompany.pageobjects.LandingPage;
import walidkerdouncompany.pageobjects.OrderPage;
import walidkerdouncompany.pageobjects.ProductCatalogue;
import walidkerdouncompany.pageobjects.CheckoutPage;
import walidkerdouncompany.pageobjects.ConfirmationPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups= {"Purchase"})
	//public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
		
		String countryName = "india";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match); // Asertion and validation can't go on Page Object files
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	// To verify ZARA COAT 3 is displaying in orders page
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("testseleniumrh@gmail.com", "Test@selenium1*");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object [][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\walidkerdouncompany\\data\\PurchaseOrder.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}};
		
	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "testseleniumrh@gmail.com");
//		map.put("password", "Test@selenium1*");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
//		
//		return new Object [][] {{map}, {map1}};
//	}
	
//	@DataProvider
//	public Object[][] getData()
//	{	
//		return new Object [][] {{"testseleniumrh@gmail.com", "Test@selenium1*", "ZARA COAT 3"}, 
//			{"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
//	}

}
