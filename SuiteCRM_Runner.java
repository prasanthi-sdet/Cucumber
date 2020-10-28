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
		features = {"Features\\SuiteCRM.feature"},
		//features = "Features",
		glue = {"stepDefinitions"},
		tags= "@SuiteCRMActivity",
		dryRun = false,
		monochrome = true,
		stepNotifications = true,
		plugin = {"pretty", "html:C:\\Users\\PrasanthiChippidi\\Documents\\Personel\\SDET\\Cucumber\\Reports\\CRM_Report.html"}
		)

public class SuiteCRM_Runner {}