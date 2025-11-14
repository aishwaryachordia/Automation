package ecom.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import ecom.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	By csMessage=By.xpath("//button[contains(text(),'Continue Shopping')]");
	
	@FindBy(xpath="//div[@class='cart']//h3")
	List<WebElement> productsincart;
	
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkoutbutton;
	
	public boolean verifyProductDisplay(String productName) {
		waitForElementToAppear(csMessage);
		boolean match=productsincart.stream().anyMatch(pr->pr.getText().equals(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut() {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		checkoutbutton.click();
		CheckOutPage checkoutpage= new CheckOutPage(driver);
		return checkoutpage;
		
	}
	
	//w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Continue Shopping')]")));
	//List<WebElement> productsincart=driver.findElements(By.xpath("//div[@class='cart']//h3"));
	//boolean match=productsincart.stream().anyMatch(pr->pr.getText().equals("ZARA COAT 3"));
	//System.out.println(match);
	//Assert.assertTrue(match);
	//driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();

}
