@SuiteCRMActivity
Feature: Test SuiteCRM

@Activity1
Scenario: Open the homepage and count the number of the dashlets on the page
    Given Open the browser to the ​Alchemy CRM​ site and login
    Then Count the number of Dashlets on the homepage
    And Print the number and title of each Dashlet into the console
    And Close the browser
    
@Activity2
Scenario: Open the Leads page and add multiple lead accounts using values passed from Feature file
    Given Open the browser to the ​Alchemy CRM​ site and login
    Then Navigate to Sales -> Leads -> Create Lead
    And Fill in the "Sales", "Leadpr005" details to create lead accounts using the values passed from the Feature file
    Then Click Save to finish
    And Navigate to the View Leads page to see results "Sales Leadpr005"
    And Close the browser
    
@Activity3
Scenario Outline: To schedule a meeting and include at least 3 invites
    Given Open the browser to the ​Alchemy CRM​ site and login
    Then Navigate to Activities -> Meetings -> Schedule a Meeting
    And Enter the details of the meeting "<meetingSubject>"
    And Search for members and add them to the meeting "<member_1>", "<member_2>", "<member_3>"
    And Click Save
    And Navigate to View Meetings page and confirm creation of the meeting "<meetingSubject>"
    And Close the browser
    
    
Examples: 
      | meetingSubject | member_1 | member_2 | member_3 |
      | SDETMeeting_001 | invitees_add_1 | invitees_add_2 | invitees_add_3 |
      
 
@Activity4
Scenario Outline: To use an Examples table to add products
    Given Open the browser to the ​Alchemy CRM​ site and login
    Then Navigate to All -> Products-> Create Product
    And Enter the details of the product "<productName>"
    And Click Save to finish
    And Go to the View Products page to see all products listed "<productName>"
    And Close the browser

    
Examples: 
      | productName |
      | Product 001 |