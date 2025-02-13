@Smoke
Feature: Validate Hana Viewer Login functionality
  This feature verify the login page and perform login functionality.

  Scenario Outline:Login Hana Viewer App

  #Step1:
    Given Launch Hana Viewer Mobile App
    When User clicks the Hana Viewers App
    Then Hana viewer Home Page should be displayed

  #Step 2:
    When Verify whether the Dispatch Login button is displaying or not
    Then Dispatch login button should be displayed

  #Step3:
    When User Click on the Dispatch Login button
    Then User should be navigated to the Dispatch login page

  #Step4:
    When Verify whether the Dispatch login button is disabled or not
    Then Respective button should be displayed

  #Step5:
    When Enter a valid "<UserName>" and "<Password>"
    Then Entered Data should be displayed in the respective fields

  #Step6:
    When Verify whether the Dispatch Login button is enabled or not
    Then Respective button should be enabled

  #Step7:
    When Click on the Dispatch Login button
    Then User should be navigate to the Trips Page.

    Examples:
      | UserName | Password |
      | Liambj   | Test@123 |