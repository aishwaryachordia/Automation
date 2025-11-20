package ecom.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecom.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryinput;
	
	@FindBy(xpath="//section[contains(@class,'ta-results')]//button")
	List<WebElement> countries;
	
	@FindBy(xpath="//a[contains(@class,'action__submit')]")
	WebElement placeorder;
	
	public void selectCountry(String countryName) {
		countryinput.click();
		countryinput.sendKeys(countryName);
		for(WebElement cont:countries) {
			if(cont.getText().equals(countryName)) {
				cont.click();
				break;
			}		
		}
	}
	
	//WebElement countryinput=driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
	//countryinput.click();
	//countryinput.sendKeys("ind");
	//List<WebElement> countries=driver.findElements(By.xpath("//section[contains(@class,'ta-results')]//button"));
	//System.out.println(countries);
	//Can also be achieved using Actions class
	//for(WebElement cont:countries) {
		//if(cont.getText().equalsIgnoreCase("india")) {
			//cont.click();
			//break;
		//}
	//}
	
	public ConfirmationPage submitOrder() {
		placeorder.click();
		ConfirmationPage confirmpage= new ConfirmationPage(driver);
		return confirmpage;
	}
}
