 package Runnertestchaima276;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
@CucumberOptions(
		
		features=".//FeaturesTR/testchaima276.feature",
		glue="testchaima276",
plugin= {"pretty","html:HTML-Reports"},
	    monochrome=true		
		)


public class testchaima276 {

}
 