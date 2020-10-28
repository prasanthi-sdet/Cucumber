Feature: Test Alchemy Jobs

@Activity1
Scenario: Visit the site’s backend and create a new user
	Given Open a browser
    When Navigate to Alchemy Jobs and log in
    Then Locate the left hand menu and click the menu item that says Users
    And Locate the Add New button and click it
    Then Fill in the necessary details
    And Click the Add New User button
    Then Verify that the user was created
    And Close Browser
    
@Activity2
Scenario: Searching for jobs and applying to them using XPath
    Given Open browser with Alchemy Jobs site​ and navigate to Jobs page
    And Find the Keywords search input field
    Then Type in keywords to search for jobs and change the Job type
    And Find the filter using XPath and filter job type to show only Full Time jobs
    Then Find a job listing using XPath and it to see job details
    And Find the title of the job listing using XPath and print it to the console
    Then Find and Click on the “Apply for job” button
    And Close Browser
    
@Activity3
Scenario: Post a job using details passed from the Feature file
    Given Open browser with Alchemy Jobs site
    When Go to Post a Job page
    Then Enter the "prusername02@gmail.com", "Automation Tester 01", "Hyderabad", "This is test job", "prusername02@gmail.com", "IBM India Pvt. Ltd." details and click on the Preview button
    Then Click submit
    Then Go to the Jobs page
    And Confirm job listing "Automation Tester 01" is shown on page
    And Close Browser
      
 
@Activity4
Scenario Outline: Rewrite activity 3 using a Scenario Outline and Examples table to post a job
    Given Open browser with Alchemy Jobs site
    When Go to Post a Job page
    Then Enter the "<email>", "<jobTitle>", "<location>", "<description>", "<applicationEmail>", "<companyName>" details and click on the Preview button
    Then Click submit
    Then Go to the Jobs page
    And Confirm job listing "<jobTitle1>" is shown on page
    And Close Browser
    

Examples:
      | email | jobTitle | location | description | applicationEmail | companyName | jobTitle1 |
      | prusername03@gmail.com | Automation Tester 01 | Hyderabad | This is test job | prusername03@gmail.com | IBM India Pvt. Ltd. | Automation Tester 01 |
      | prusername04@gmail.com | Automation Tester 02 | Chennai | This is test job | prusername04@gmail.com | IBM India Pvt. Ltd. | Automation Tester 02 |