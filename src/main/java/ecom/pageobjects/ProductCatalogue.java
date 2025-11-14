package ecom.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecom.AbstractComponents.AbstractComponent;

//PageObject should not hold any data, it should only hold actions and elements/locators
public class ProductCatalogue extends AbstractComponent{
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		//sending from child to parent
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//List<WebElement> products= driver.findElements(By.xpath("//div[@class='card-body']"));
	//WebElement prod=products.stream().filter(product->product.findElement(By.xpath("//h5//b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	//prod.findElement(By.xpath("//button[2]")).click();
	
	//WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(10));
	//w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	//Assert.assertEquals(driver.findElement(By.id("toast-container")).getText(),"Product Added To Cart");
	//w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	
	//if its driver. then only you can write PageFactory
	@FindBy(xpath="//div[@class='card-body']")
	//@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	//By addToCart=By.xpath("//button[2]");
	By addToCart=By.xpath(".//button[contains(text(),'Add To Cart')]");
	By toastMessage=By.id("toast-container");
	
	public List<WebElement> getProductsList() {
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod=getProductsList().stream().filter(product->product.findElement(By.xpath(".//b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		//WebElement prod=getProductsList().stream().filter(product->product.findElement(By.xpath("//h5//b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		System.out.println(prod);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
	}
		
}
