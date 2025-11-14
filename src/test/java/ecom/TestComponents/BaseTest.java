package ecom.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecom.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {

	//properties class
	Properties prop= new Properties();
	FileInputStream fis= new FileInputStream("C://Users//hp//eclipse-workspace//SeleniumFrameworkDesign//src//main//java//ecom//resources//GlobalData.properties");
	prop.load(fis);
	String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
	prop.getProperty("browser");
	
	if(browserName.equals("firefox"))
	{
	driver= new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	driver.manage().window().maximize();
	WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(10));	
	return driver;
}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//String jsonContent= FileUtils.readFileToString(new File("C:\Users\hp\eclipse-workspace\SeleniumFrameworkDesign\src\test\java\rahulshettyacademy\data\PurchaseOrder.json"));
		String jsonContent= FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\src\test\java\rahulshettyacademy\data\PurchaseOrder.json"));

		//String to HashMap Jackson Databind
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){	
		});
		return data;	
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		//File destn=new File(System.getProperty("C://Users//hp//eclipse-workspace//SeleniumFrameworkDesign//reports//")+testCaseName+".png");
		//File destn=new File(System.getProperty("user.dir")+"//reports"+testCaseName+".png");
		File destn=new File(System.getProperty("user.dir")+"//reports//testreport.png");
		FileUtils.copyFile(source, destn);
		//return System.getProperty("C://Users//hp//eclipse-workspace//SeleniumFrameworkDesign//reports//")+testCaseName+".png";
		//return System.getProperty("user.dir")+"//reports"+testCaseName+".png";
		return System.getProperty("user.dir")+"//reports//testreport.png";

	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver= initializeDriver();
		landingPage= new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}
