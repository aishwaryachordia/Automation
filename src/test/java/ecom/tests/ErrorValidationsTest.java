package ecom.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.TestComponents.BaseTest;
import ecom.TestComponents.Retry;
import ecom.pageobjects.CartPage;
import ecom.pageobjects.CheckOutPage;
import ecom.pageobjects.ConfirmationPage;
import ecom.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

		@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
		public void errorValidation() throws IOException {
			String productName="ZARA COAT 3";
			landingPage.loginApplication("aishwarya@1234.com","Admin@1234");
			Assert.assertEquals("Incorrect email password.",landingPage.getErrorMessage());
		}
		
		@Test
		public void productErrorValidation() throws IOException {
			String productName="ZARA COAT 3";
			ProductCatalogue productCatalogue=landingPage.loginApplication("aishwarya@123.com","Admin@1234");
			List<WebElement> products=productCatalogue.getProductsList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage=productCatalogue.goToCartpage();
			Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
			if(match==false)
			System.out.println("ZARA COAT 3 is wrongly verified as ZARA COAT 33");
			Assert.assertFalse(match);
		}	
}



















