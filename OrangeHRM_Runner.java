package cucumberTest;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"Features\\OrangeHRM.feature"},
		glue = {"stepDefinitions"},
		dryRun = false,
		monochrome = true,
		stepNotifications = true,
		plugin = {"pretty","html:target/cucumber-reports/OrangeHRM.html"}

		)
public class OrangeHRM_Runner {}
