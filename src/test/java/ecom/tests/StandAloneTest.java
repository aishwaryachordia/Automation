package ecom.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ecom.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
		WebDriver driver= new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		LandingPage landingPage= new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("aishwarya@123.com");
		driver.findElement(By.id("userPassword")).sendKeys("Admin@1234");
		driver.findElement(By.id("login")).click();
		//List<WebElement> products= driver.findElements(By.xpath("//div[@class='card-body']//h5//b"));
		//WebElement prod=products.stream().map(product->product.getText().equals("ZARA COAT 3"));
		List<WebElement> products= driver.findElements(By.xpath("//div[@class='card-body']"));
		WebElement prod=products.stream().filter(product->product.findElement(By.xpath("//h5//b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		//the control is in "//div[@class='card-body']" only as filter is just used to filter while map would transform
		prod.findElement(By.xpath("//button[2]")).click();
		
		WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		Assert.assertEquals(driver.findElement(By.id("toast-container")).getText(),"Product Added To Cart");
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Continue Shopping')]")));
		List<WebElement> productsincart=driver.findElements(By.xpath("//div[@class='cart']//h3"));
		boolean match=productsincart.stream().anyMatch(pr->pr.getText().equals("ZARA COAT 3"));
		System.out.println(match);
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		WebElement countryinput=driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		countryinput.click();
		countryinput.sendKeys("ind");
		List<WebElement> countries=driver.findElements(By.xpath("//section[contains(@class,'ta-results')]//button"));
		//List<WebElement> countries=driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']//button"));
		System.out.println(countries);
		//Can also be achieved using Actions class
		for(WebElement cont:countries) {
			if(cont.getText().equalsIgnoreCase("india")) {
				cont.click();
				break;
			}
		}
		driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
		String heading=driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(heading.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}

}
