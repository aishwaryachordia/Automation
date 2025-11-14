package ecom.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ecom.TestComponents.BaseTest;
import ecom.pageobjects.CartPage;
import ecom.pageobjects.CheckOutPage;
import ecom.pageobjects.ConfirmationPage;
import ecom.pageobjects.LandingPage;
import ecom.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmpage;
	
	@Given("I landed on ECommerce Page")
	public void I_landed_on_ECommerce_Page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue=landingPage.loginApplication(username,password);
	}
	
	@When("I add product (.+) to Cart$")
	public void add_product_to_cart(String productName) {
		List<WebElement> products=productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);	
	}
	
	@When("Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName){
		CartPage cartPage=productCatalogue.goToCartpage();
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartPage.goToCheckOut();
		checkoutpage.selectCountry("India");
		confirmpage=checkoutpage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage") 
	public void message_displayed_confirmationPage(String string) {
		String message=confirmpage.confirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String str1) throws Throwable {
		Assert.assertEquals(str1, landingPage.getErrorMessage());
		driver.close();
	}
	
}
