package stepDefinitions;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlchemyJobs {
	
	WebDriver driver;
	WebDriverWait wait;
	String userName = "Username_" + new Random().nextInt(10000);
	String emailId = userName + "@gmail.com";
	
	@Given("^Open browser with Alchemy Jobs site$")
	public void openBrowser1() throws Throwable {
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\PrasanthiChippidi\\Documents\\Personel\\SDET\\geckodriver-v0.27.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("https://alchemy.hguy.co/jobs");
		driver.manage().window().maximize();
	}



	@When("^Go to Post a Job page$")
	public void goToPostAJobPage() {

		driver.findElement(By.linkText("Post a Job")).click();

	}
	

	@Then("^Enter the \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\" details and click on the Preview button$")
	public void fillJobDetails(String email, String jobTitle, String location, String description, String applicationEmail, String companyName) {

		driver.findElement(By.id("create_account_email")).clear();
		driver.findElement(By.id("create_account_email")).sendKeys(email);
		driver.findElement(By.id("job_title")).clear();
		driver.findElement(By.id("job_title")).sendKeys(jobTitle);
		driver.findElement(By.id("job_location")).clear();
		driver.findElement(By.id("job_location")).sendKeys(location);
		driver.findElement(By.id("job_location")).clear();
		driver.findElement(By.id("job_location")).sendKeys(location);
		
		WebElement iframe = driver.findElement(By.id("job_description_ifr"));
		driver.switchTo().frame(iframe);
		driver.findElement(By.xpath("/html/body")).sendKeys(description);
		driver.switchTo().defaultContent();
		driver.findElement(By.id("application")).clear();
		driver.findElement(By.id("application")).sendKeys(applicationEmail);
		driver.findElement(By.id("company_name")).clear();
		driver.findElement(By.id("company_name")).sendKeys(companyName);
		driver.findElement(By.name("submit_job")).click();

	}



	@Then("^Click submit$")
	public void clickOnSubmitButton() {

		driver.findElement(By.id("job_preview_submit_button")).click();

	}



	@Then("^Go to the Jobs page$")
	public void gotoJobsPage() {

		driver.findElement(By.linkText("Jobs")).click();

	}



	@And("^Confirm job listing \"(.*)\" is shown on page$")
	public void confirmJobListing(String jobTitle1) {

		driver.findElement(By.linkText("Jobs")).click();
		driver.findElement(By.id("search_keywords")).clear();
		driver.findElement(By.id("search_keywords")).sendKeys(jobTitle1);
		driver.findElement(By.xpath("//input[@value='Search Jobs']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/h3[text()='" + jobTitle1 + "']")));
		String actualJobTitle = driver.findElement(By.xpath("//div/h3[text()='" + jobTitle1 + "']")).getText();
		Assert.assertEquals(actualJobTitle, jobTitle1);

	}
	
	@Given("^Open a browser$")
	public void openBrowser2() throws Throwable {
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\PrasanthiChippidi\\Documents\\Personel\\SDET\\geckodriver-v0.27.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);

	}


	@When("^Navigate to Alchemy Jobs and log in$")
	public void logIn() throws Throwable {

		driver.get("https://alchemy.hguy.co/jobs/wp-admin");
		driver.manage().window().maximize();
		driver.findElement(By.id("user_login")).clear();
		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).clear();
		driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("wp-submit")).click();

	}



	@Then("^Locate the left hand menu and click the menu item that says Users$")
	public void clickOnUsers() {

		driver.findElement(By.xpath("//a/div[text()='Users']")).click();

	}



	@And("^Locate the Add New button and click it$")
	public void clickAddNewButton() {

		driver.findElement(By.linkText("Add New")).click();

	}



	@Then("^Fill in the necessary details$")
	public void fillInUserDetails() {

		driver.findElement(By.id("user_login")).clear();
		driver.findElement(By.id("user_login")).sendKeys(userName);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(emailId);

	}



	@And("^Click the Add New User button$")
	public void clickAddNewUserButton() {

		driver.findElement(By.id("createusersub")).click();

	}



	@Then("^Verify that the user was created$")
	public void verifyUserCreation() {

		driver.findElement(By.id("user-search-input")).clear();
		driver.findElement(By.id("user-search-input")).sendKeys(userName);
		driver.findElement(By.id("search-submit")).click();
		String actualUserName = driver.findElement(By.linkText(userName)).getText();
		Assert.assertEquals(actualUserName, userName);

	}
	
	@Given("^Open browser with Alchemy Jobs site​ and navigate to Jobs page$")
	public void openBrowser() throws Throwable {

		System.setProperty("webdriver.gecko.driver", "C:\\Users\\PrasanthiChippidi\\Documents\\Personel\\SDET\\geckodriver-v0.27.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("https://alchemy.hguy.co/jobs");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Jobs")).click();

	}



	@And("^Find the Keywords search input field$")
	public void findKeywordSearchTextBox() {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("search_keywords")));

	}



	@Then("^Type in keywords to search for jobs and change the Job type$")
	public void searchForJobs() {

		driver.findElement(By.id("search_keywords")).clear();
		driver.findElement(By.id("search_keywords")).sendKeys("Cucumber/BDD Tester");
		driver.findElement(By.xpath("//input[@value='Search Jobs']")).click();

	}



	@And("^Find the filter using XPath and filter job type to show only Full Time jobs$")
	public void filterByFullTimeJobs() {

		driver.findElement(By.id("job_type_freelance")).click();
		driver.findElement(By.id("job_type_internship")).click();
		driver.findElement(By.id("job_type_part-time")).click();
		driver.findElement(By.id("job_type_temporary")).click();

	}



	@Then("^Find a job listing using XPath and it to see job details$")
	public void openJobDetails() {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(@href,'ibm-hyderabad-2-cucumber-bdd-tester-4')]")));
		driver.findElement(By.xpath("//a[contains(@href,'ibm-hyderabad-2-cucumber-bdd-tester-4')]")).click();

	}



	@And("^Find the title of the job listing using XPath and print it to the console$")
	public void printJobTitle() {

		String getJobTitle = driver.findElement(By.className("entry-title")).getText();
		Assert.assertEquals(getJobTitle, "Cucumber/BDD Tester");

	}



	@Then("^Find and Click on the “Apply for job” button$")
	public void applyForJob() {

		driver.findElement(By.xpath("//input[@value='Apply for job']")).click();

	}



	@And("^Close Browser$")
	public void closeTheBrowser() throws Throwable {

		driver.quit();

	}



}