package ecom.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import ecom.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
		
	@FindBy(xpath="//tbody//tr//td[2]")
	List<WebElement> productsinorders;
	
	public boolean verifyOrderDisplay(String productName) {
		boolean match=productsinorders.stream().anyMatch(pr->pr.getText().equalsIgnoreCase(productName));
		return match;
	}
	

}
