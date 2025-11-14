Feature:Error Validation
I want to use this template for my feature file

Background:
Given I landed on ECommerce Page

@ErrorValidation
Scenario Outline:
	Given Logged in with username <name> and password <password>
	Then "Incorrect email or password." message is displayed
	
Examples:
| name               | password   | 
| aishwarya@123.com  | Admin@12 | 