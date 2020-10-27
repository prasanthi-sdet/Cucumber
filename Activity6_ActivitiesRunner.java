package cucumberTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
	    features = "Features",
	    glue = {"stepDefinitions"},
	    tags = "@activity1",
	    strict = true,
	    //plugin = {"pretty"},
	    plugin = {"pretty","html:target/cucumber-reports/test-report.html"},
	    //plugin = {"json: test-reports/json-report.json"},
	    monochrome = true
	)
public class Activity6_ActivitiesRunner {

}
