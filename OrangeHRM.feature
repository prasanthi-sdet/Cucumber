Feature: Test OrangeHRM

@Activity1
Scenario: Creating a job vacancy
    Given Open the OrangeHRM​ page and login
    When Navigate to the Recruitment page
    Then Click on the Vacancies menu item to navigate to the vacancies page
    And Click on the Add button to navigate to the Add Job Vacancy form
    Then Fill out the necessary details
    And Click Save
    Then Verify that the vacancy was created
    And Close the browser

@Activity2
Scenario: Add information about a candidate for recruitment
    Given Open the OrangeHRM​ page and login
    When Navigate to the Recruitment page
    Then Click on Add Button and fill in the details of the candidate
    And Upload a resume docx or pdf to the form
    And Click Save
    	Then Navigate back to the Recruitments page to confirm candidate entry
    And Close the browser

@Activity3
Scenario Outline: Add multiple employees using an the Examples table
    Given Open the OrangeHRM​ page and login
    When Find the PIM option in the menu and click it
    Then Click the Add button to add a new Employee
    And Make sure the Create Login Details checkbox is checked
    Then Fill in the "<firstName>", "<lastName>", "<userName>", "<password>", "<confirmPassword>" and click Save
    Then Verify that the employees have been created "<employeeName>"
    And Close the browser
    

Examples:
      | firstName | lastName | userName | password | confirmPassword | employeeName |
      | abc 004 | pr | prabc002 | empl0ypwd | empl0ypwd | abc 004 pr |
      | XYZ 004 | PRQ | prXYZ002 | empl0ypwd | empl0ypwd | XYZ 004 PRQ |

@Activity4
Scenario Outline: Creating multiple vacancies using data from an Examples table
    Given Open the OrangeHRM​ page and login
    When Navigate to the Recruitment page
    Then Click on the Vacancies menu item to navigate to the vacancies page
    And Click on the Add button to navigate to the Add Job Vacancy form
    Then Fill out the necessary details "<vacancyName>"
    And Click Save
    Then Verify that the vacancy was created "<vacancyName>"
    And Close the browser
    

Examples:
      | vacancyName |
      | PrSDET_Tester_003 |
      | PrAutomation_Tester_003 |
