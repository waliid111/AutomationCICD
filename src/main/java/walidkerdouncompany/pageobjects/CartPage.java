package walidkerdouncompany.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import walidkerdouncompany.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutElement;
	
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match = productTitles.stream()
				.anyMatch(nameCardProduct -> nameCardProduct.getText().equalsIgnoreCase(productName));
		
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutElement.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		
		return checkoutPage;
	}
	

}
