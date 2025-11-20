package ecom.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ecom.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a[contains(@class,'action__submit')]")
	WebElement placeorder;
	
	@FindBy(className="hero-primary")
	WebElement confirmationMessage;

	public String confirmationMessage() {
		return confirmationMessage.getText();
	}
	
	//String heading=driver.findElement(By.className("hero-primary")).getText();
	//Assert.assertTrue(heading.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	//driver.close();
}
