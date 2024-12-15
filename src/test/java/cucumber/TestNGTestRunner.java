package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Cucumber -> to run it it depends on TestNG or JUnit based on how the code is written
// we use TestNGrunner to just run the features Files

@CucumberOptions(features="src/test/java/cucumber", glue="walidkerdouncompany.stepDefinition",
monochrome=true, tags= "@Regression", plugin= {"html:target/cucumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	

}
