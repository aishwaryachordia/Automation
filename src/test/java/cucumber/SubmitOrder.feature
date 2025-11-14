Feature:Purchase the order from ECommerce Website
I want to use this template for my feature file

Background:
Given I landed on ECommerce Page

@Regression
Scenario Outline:Positive test of submitting the order
	Given Logged in with username <name> and password <password>
	When I add product <productName> to Cart
	And Checkout <productName> and submit the order
	Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage 
	
Examples:
| name               | password   | productName |
| aishwarya@123.com  | Admin@1234 | ZARA COAT 3 |