package cucumberTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "Features",
    glue = {"stepDefinitions"},
    tags = "@activity2",
    monochrome=true
)
public class Activity2_ActivitiesRunner {

}
