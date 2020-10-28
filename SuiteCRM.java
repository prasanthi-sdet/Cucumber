package stepDefinitions;

import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;

public class SuiteCRM {
	
	WebDriver driver;
	WebDriverWait wait;

	@Given("^Open the browser to the ​Alchemy CRM​ site and login$")
	public void openBrowser() throws Throwable {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\PrasanthiChippidi\\Documents\\Personel\\SDET\\geckodriver-v0.27.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("https://alchemy.hguy.co/crm/");
		driver.manage().window().maximize();

		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_name")).sendKeys("admin");

		driver.findElement(By.id("username_password")).clear();
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");

		driver.findElement(By.id("bigbutton")).click();
	}

	

	@Then("^Count the number of Dashlets on the homepage$")
	public void countNumberOfDashlets() {
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dashlet-title")));
		List<WebElement> dashlets = driver.findElements(By.cssSelector(".dashlet-title"));
		System.out.println("Total number of Dashlets are: " + dashlets.size());

	}



	@And("^Print the number and title of each Dashlet into the console$")
	public void printNumberAndDashletTitle() {
		
		List<WebElement> dashletTitles = driver.findElements(By.xpath("//td[@class='dashlet-title']/h3/span[contains(@class,'suitepicon-module-')]/following-sibling::span"));
		for (WebElement dashletTitle : dashletTitles) {
			System.out.println("Dashlet Title is: " + dashletTitle.getText());
		}		

		List<WebElement> dashletNumbers = driver.findElements(By.cssSelector(".pageNumbers"));
		for (WebElement dashletNumber : dashletNumbers) {
			System.out.println("Dashlet Number is: " + dashletNumber.getText());
		}

 	}
	
	@Then("^Navigate to Sales -> Leads -> Create Lead$")
	public void navigateToCreateLead() {
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dashlet-title")));
		WebElement salesLink = driver.findElement(By.id("grouptab_0"));
		Actions action = new Actions(driver);
		action.moveToElement(salesLink).build().perform();
		driver.findElement(By.id("moduleTab_9_Leads")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='Create Lead']")));
		driver.findElement(By.xpath("//div[text()='Create Lead']")).click();

	}



	@And("^Fill in the \"(.*)\", \"(.*)\" details to create lead accounts using the values passed from the Feature file$")
	public void fillCreateLeadForm(String firstName, String lastName) throws InterruptedException {

		//Thread.sleep(3500);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("first_name"))).click();		

		driver.findElement(By.id("first_name")).clear();
		driver.findElement(By.id("first_name")).sendKeys(firstName);

		driver.findElement(By.id("last_name")).clear();
		driver.findElement(By.id("last_name")).sendKeys(lastName);

 	}

	

	@Then("^Click Save to finish$")
	public void clickOnSave() {
		
		driver.findElement(By.id("SAVE")).click();
		
	}
	

	@And("^Navigate to the View Leads page to see results \"(.*)\"$")
	public void navigateToViewLeads(String leadName) throws InterruptedException {		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='View Leads']")));
		driver.findElement(By.xpath("//div[text()='View Leads']")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(leadName)));
		String actualLeadName = driver.findElement(By.linkText(leadName)).getText();
		Assert.assertEquals(actualLeadName, leadName);
	}
	

	@Then("^Navigate to Activities -> Meetings -> Schedule a Meeting$")
	public void navigateToScheduleMeeting() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dashlet-title")));
		WebElement salesLink = driver.findElement(By.id("grouptab_3"));
		Actions action = new Actions(driver);
		action.moveToElement(salesLink).build().perform();
		driver.findElement(By.id("moduleTab_9_Meetings")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='Schedule Meeting']")));
		driver.findElement(By.xpath("//div[text()='Schedule Meeting']")).click();
	}



	@And("^Enter the details of the meeting \"(.*)\"$")
	public void fillMeetingDetails(String meetingSubject) throws InterruptedException {
		//Thread.sleep(3500);	
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("name")));
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(meetingSubject);
 	}

	

	@And("^Search for members and add them to the meeting \"(.*)\", \"(.*)\", \"(.*)\"$")
	public void addMembers(String member_1, String member_2, String member_3) {
		WebElement scroll = driver.findElement(By.id("invitees_search"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
		scroll.click();	
		
		driver.findElement(By.id("search_last_name")).clear();
		driver.findElement(By.id("search_last_name")).sendKeys("Leadpr");
		
		driver.findElement(By.id("invitees_search")).click();
		
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(member_1)));
		//driver.findElement(By.id(member_1)).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id(member_1)));
		driver.findElement(By.id(member_1)).click();
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(member_2)));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id(member_2)));
		driver.findElement(By.id(member_2)).click();
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(member_3)));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id(member_3)));
		driver.findElement(By.id(member_3)).click();

	}

	

	@Then("^Click Save$")
	public void clickOnSaveButton() {
		WebElement scroll = driver.findElement(By.id("btnSave"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
		scroll.click();

	}

	

	@And("^Navigate to View Meetings page and confirm creation of the meeting \"(.*)\"$")
	public void navigateToViewMeeting(String meetingSubject) throws InterruptedException {
		//Thread.sleep(3500);

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='View Meetings']")));
		driver.findElement(By.xpath("//div[text()='View Meetings']")).click();	
		//Thread.sleep(3500);	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(meetingSubject)));
		String actualMeetingSubject = driver.findElement(By.linkText(meetingSubject)).getText();
		Assert.assertEquals(actualMeetingSubject, meetingSubject);

	}
	
	
	@Then("^Navigate to All -> Products-> Create Product$")
	public void navigateToCreateProduct() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".dashlet-title")));
		WebElement salesLink = driver.findElement(By.id("grouptab_5"));
		Actions action = new Actions(driver);
		action.moveToElement(salesLink).build().perform();
		driver.findElement(By.linkText("Products")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='Create Product']")));
		driver.findElement(By.xpath("//div[text()='Create Product']")).click();
	}



	@And("^Enter the details of the product \"(.*)\"$")
	public void fillProductDetails(String productName) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("name")));
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(productName);
 	}

	

	@And("^Go to the View Products page to see all products listed \"(.*)\"$")
	public void navigateToViewProducts(String productName) throws InterruptedException {
		//Thread.sleep(3500);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='View Products']")));
		driver.findElement(By.xpath("//div[text()='View Products']")).click();		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(productName)));
		String actualProductName = driver.findElement(By.linkText(productName)).getText();
		Assert.assertEquals(actualProductName, productName);
	}


	@And("^Close the browser$")
	public void closeTheBrowser() throws Throwable {
		driver.quit();
	}



}