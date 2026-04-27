Feature: Text Box Verification 

Scenario: Text data with fullName Amey Kamde
  Given user is on text box page
  When user enters fullname "Amey Kamde" and email "ameykamde@gmail.com"
And user also enters current Address "Ycce" and permanent address "YCCE"
  And user clicks on submit button
  Then text data result should be verified