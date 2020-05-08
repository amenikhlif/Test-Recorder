 package Runnertesttest269;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
		
		features=".//FeaturesTR/testtest269.feature",
		glue="testtest269",
plugin= {"pretty","html:HTML-Reports"},
	    monochrome=true		
		)


public class testtest269 {

}
 