package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//cucumber does not run on its own, need to use testng or junit
@CucumberOptions(features="src/test/java/cucumber",glue="ecom.stepDefinitions",monochrome=true,tags="@Regression",plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}
