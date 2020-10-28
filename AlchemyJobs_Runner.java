package cucumberTest;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/*@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Features",
		glue = {"stepDefinitions"},
		tags = "@Activity1",
		dryRun = false,
		monochrome = true,
		stepNotifications = true,
		plugin = {"pretty", "html:C:\\Users\\PrasanthiChippidi\\Documents\\Personel\\SDET\\Cucumber\\Reports\\CRM_Report.html"}
		)*/

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"Features\\AlchemyJobs.feature"},
		//features = "Features",
		glue = {"stepDefinitions"},
		//tags="@Activity2",
		dryRun = false,
		monochrome = true,
		stepNotifications = true,
		plugin = {"pretty", "html:C:\\Users\\PrasanthiChippidi\\Documents\\Personel\\SDET\\Cucumber\\Reports\\CRM_Report.html"}
		)

public class AlchemyJobs_Runner {}