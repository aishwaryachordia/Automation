package ecom.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecom.TestComponents.BaseTest;
import ecom.pageobjects.CartPage;
import ecom.pageobjects.CheckOutPage;
import ecom.pageobjects.ConfirmationPage;
import ecom.pageobjects.LandingPage;
import ecom.pageobjects.OrderPage;
import ecom.pageobjects.ProductCatalogue;

//Only data is going to be present in this TestNG file 
public class SubmitOrderTest extends BaseTest{
		//String productName="ZARA COAT 3";

		@Test(dataProvider="getData",groups= {"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException {
			
			//WebDriver driver= new FirefoxDriver();
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//driver.manage().window().maximize();
			//WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(10));
			
			//LandingPage landingPage= new LandingPage(driver);
			//landingPage.goTo();
			//LandingPage landingPage=launchApplication();
			ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
			//ProductCatalogue productCatalogue=landingPage.loginApplication("aishwarya@123.com","Admin@1234");
			List<WebElement> products=productCatalogue.getProductsList();
			productCatalogue.addProductToCart(input.get("product"));
			CartPage cartPage=productCatalogue.goToCartpage();
			
			Boolean match=cartPage.verifyProductDisplay(input.get("product"));
			Assert.assertTrue(match);
			CheckOutPage checkoutpage=cartPage.goToCheckOut();
			checkoutpage.selectCountry("India");		
			
			ConfirmationPage confirmpage=checkoutpage.submitOrder();
			String message=confirmpage.confirmationMessage();
			Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			//driver.close();
		}
		
		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest() {
			String productName="ZARA COAT 3";
			ProductCatalogue productCatalogue=landingPage.loginApplication("aishwarya@123.com","Admin@1234");
			OrderPage orderPage=productCatalogue.goToOrdersPage();
			boolean match=orderPage.verifyOrderDisplay(productName);
			Assert.assertTrue(match);
		}
		
		@DataProvider
		public Object[][] getData() throws IOException {
			List<HashMap<String,String>> data=getJsonDataToMap("C://Users//hp//eclipse-workspace//SeleniumFrameworkDesign//src//test//java//ecom//data//PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
		
		
		//Extent Reports
		
		
		/*HashMap<String, String> map= new HashMap<String, String>();
		map.put("email","aishwarya@123.com");
		map.put("password","Admin@1234");
		map.put("product","ZARA COAT 3");
		
		HashMap<String, String> map1= new HashMap<String, String>();
		map1.put("email","aishwarya@123.com");
		map1.put("password","Admin@1234");
		map1.put("product","ADIDAS ORIGINAL");*/
	
}
