 package Runnertestmardi22281;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
		
		features=".//FeaturesTR/testmardi22281.feature",
		glue="testmardi22281",
plugin= {"pretty","html:HTML-Reports"},
	    monochrome=true		
		)


public class testmardi22281 extends AbstractTestNGCucumberTests {

}
 