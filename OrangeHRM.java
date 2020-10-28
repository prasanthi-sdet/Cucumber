package stepDefinitions;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrangeHRM {

	WebDriver driver;
	WebDriverWait wait;
	String vacancyName = "SDETPr_TESTER_" + new Random().nextInt(10000);
	String firstName = "FirstName_" + new Random().nextInt(10000);
	String lastName = "LastName_" + new Random().nextInt(10000);
	String email = firstName +"@gmail.com";
	String candidateName = firstName + " " + lastName;
	
	@Given("^Open the OrangeHRM​ page and login$")
	public void openBrowser() throws Throwable {
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\PrasanthiChippidi\\Documents\\Personel\\SDET\\geckodriver-v0.27.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://alchemy.hguy.co/orangehrm");
		driver.manage().window().maximize();
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
		driver.findElement(By.id("btnLogin")).click();

	}



	@When("^Navigate to the Recruitment page$")
	public void navigateToRecruitmentPage() {

		WebElement dashboardLink = driver.findElement(By.id("menu_dashboard_index"));
		WebElement recruitmentLink = driver.findElement(By.id("menu_recruitment_viewRecruitmentModule"));
		if (dashboardLink.isEnabled()) {
			recruitmentLink.click();
		} else {
			dashboardLink.click();
			recruitmentLink.click();
		}
		recruitmentLink.click();
	}



	@Then("^Click on the Vacancies menu item to navigate to the vacancies page$")
	public void navigateToVacanciesPage() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu_recruitment_viewJobVacancy")));
		driver.findElement(By.id("menu_recruitment_viewJobVacancy")).click();
	}



	@And("^Click on the Add button to navigate to the Add Job Vacancy form$")
	public void navigateToAddVacancyPage() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btnAdd")));
		driver.findElement(By.id("btnAdd")).click();
	}



	@Then("^Fill out the necessary details$")
	public void fillAddJobVacancyForm() {
		WebElement addJobVacancy_jobTitle = driver.findElement(By.id("addJobVacancy_jobTitle"));
		Select selectJobTitle = new Select(addJobVacancy_jobTitle);
		selectJobTitle.selectByValue("2");
		driver.findElement(By.id("addJobVacancy_name")).sendKeys(vacancyName);
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("Test Employee");
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(Keys.ENTER);
	}



	//@And("^Click the “Save” button to save the vacancy$")
	@And("^Click Save$")
	public void clickOnSaveButton() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btnSave")));
		driver.findElement(By.id("btnSave")).click();
	}



	@Then("^Verify that the vacancy was created$")
	public void verifyVacancyCreationResults() {
		driver.findElement(By.id("btnBack")).click();
		WebElement vacancySearch_jobTitle = driver.findElement(By.id("vacancySearch_jobTitle"));
		Select selectJobTitle = new Select(vacancySearch_jobTitle);
		selectJobTitle.selectByValue("2");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("vacancySearch_jobVacancy")));
		WebElement vacancySearch_jobVacancy = driver.findElement(By.id("vacancySearch_jobVacancy"));
		Select selectJobVacancy = new Select(vacancySearch_jobVacancy);
		selectJobVacancy.selectByVisibleText(vacancyName);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("vacancySearch_hiringManager")));
		WebElement vacancySearch_hiringManager = driver.findElement(By.id("vacancySearch_hiringManager"));
		Select selectHiringManager = new Select(vacancySearch_hiringManager);
		selectHiringManager.selectByVisibleText("Test Employee");
		driver.findElement(By.id("btnSrch")).click();
		String actualVacancyName = driver.findElement(By.linkText(vacancyName)).getText();
		Assert.assertEquals(actualVacancyName, vacancyName);
	}

	@Then("^Click on Add Button and fill in the details of the candidate$")
	public void fillAddCandidateForm() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btnAdd")));
		driver.findElement(By.id("btnAdd")).click();
		driver.findElement(By.id("addCandidate_firstName")).clear();
		driver.findElement(By.id("addCandidate_firstName")).sendKeys(firstName);
		driver.findElement(By.id("addCandidate_lastName")).clear();
		driver.findElement(By.id("addCandidate_lastName")).sendKeys(lastName);
		driver.findElement(By.id("addCandidate_email")).clear();
		driver.findElement(By.id("addCandidate_email")).sendKeys(email);		

	}



	@And("^Upload a resume docx or pdf to the form$")
	public void uploadResume() {
		String path = "C:\\Users\\PrasanthiChippidi\\Documents\\Personel\\SDET\\Eclipse-Workspace\\CucumberProjects\\src\\test\\Resources\\CV.pdf";
		driver.findElement(By.id("addCandidate_resume")).sendKeys(path);
	}



	@Then("^Navigate back to the Recruitments page to confirm candidate entry$")
	public void confirmCandidateEntry() {
		driver.findElement(By.id("btnBack")).click();
		driver.findElement(By.id("candidateSearch_candidateName")).clear();
		driver.findElement(By.id("candidateSearch_candidateName")).sendKeys(candidateName);
		driver.findElement(By.id("candidateSearch_candidateName")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("btnSrch")).click();
		String actualCandidateName = driver.findElement(By.linkText(candidateName)).getText();
		Assert.assertEquals(actualCandidateName, candidateName);
	}
	
	@When("^Find the PIM option in the menu and click it$")
	public void navigateToPIMPage() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu_dashboard_index")));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu_pim_viewPimModule")));
		WebElement dashboardLink = driver.findElement(By.id("menu_dashboard_index"));
		WebElement pimLink = driver.findElement(By.id("menu_pim_viewPimModule"));
		if (dashboardLink.isDisplayed()) {
			pimLink.click();
		} else {
			dashboardLink.click();
			pimLink.click();
		}
		pimLink.click();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}

	

	@Then("^Click the Add button to add a new Employee$")
	public void clickOnAddButton() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu_pim_addEmployee")));
		driver.findElement(By.id("menu_pim_addEmployee")).click();
	}

	

	@And("^Make sure the Create Login Details checkbox is checked$")
	public void checkTheCreateLoginDetailsCheckBox() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("chkLogin")));
		driver.findElement(By.id("chkLogin")).click();
	}

	

	@Then("^Fill in the \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\" and click Save$")
	public void fillAddEmployeeForm(String firstName, String lastName, String userName, String password, String confirmPassword) {
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_name")).sendKeys(userName);
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("re_password")).clear();
		driver.findElement(By.id("re_password")).sendKeys(confirmPassword);
		driver.findElement(By.id("btnSave")).click();
	}

	

	@Then("^Verify that the employees have been created \"(.*)\"$")
	public void verifyEmployeeCreation(String employeeName) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='profile-pic']/h1")));
		String actualEmployeeName = driver.findElement(By.xpath("//div[@id='profile-pic']/h1")).getText();
		Assert.assertEquals(actualEmployeeName, employeeName);
	}
	
	
	@Then("^Fill out the necessary details \"(.*)\"$")
	public void fillAddJobVacancyForm(String vacancyName) {
		WebElement addJobVacancy_jobTitle = driver.findElement(By.id("addJobVacancy_jobTitle"));
		Select selectJobTitle = new Select(addJobVacancy_jobTitle);
		selectJobTitle.selectByValue("3");
		driver.findElement(By.id("addJobVacancy_name")).sendKeys(vacancyName);
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("Test Employee");
		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(Keys.ENTER);
	}
	
	
	@Then("^Verify that the vacancy was created \"(.*)\"$")
	public void verifyVacancyCreation(String vacancyName) {
		driver.findElement(By.id("btnBack")).click();
		WebElement vacancySearch_jobTitle = driver.findElement(By.id("vacancySearch_jobTitle"));
		Select selectJobTitle = new Select(vacancySearch_jobTitle);
		selectJobTitle.selectByValue("3");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("vacancySearch_jobVacancy")));
		WebElement vacancySearch_jobVacancy = driver.findElement(By.id("vacancySearch_jobVacancy"));
		Select selectJobVacancy = new Select(vacancySearch_jobVacancy);
		selectJobVacancy.selectByVisibleText(vacancyName);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("vacancySearch_hiringManager")));
		WebElement vacancySearch_hiringManager = driver.findElement(By.id("vacancySearch_hiringManager"));
		Select selectHiringManager = new Select(vacancySearch_hiringManager);
		selectHiringManager.selectByVisibleText("Test Employee");
		driver.findElement(By.id("btnSrch")).click();
		String actualVacancyName = driver.findElement(By.linkText(vacancyName)).getText();
		Assert.assertEquals(actualVacancyName, vacancyName);
	}
	
	
	@And("^Close the browser$")
	public void closeTheBrowser() throws Throwable {
		driver.quit();
	}



}