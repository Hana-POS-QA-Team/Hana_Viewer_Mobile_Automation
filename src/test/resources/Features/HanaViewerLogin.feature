Feature: Validate Hana Viewer Login functionality
  This feature verify the login page and perform login functionality.

  Scenario Outline:Login Hana Viewer App
    Given Login to Hana Viewer Mobile App
    When User Enters "<UserName>" and "<Password>" and click login
    Then User Should logged into Hana Driver Login

    Examples:
      | UserName | Password |
      | Liambj    | Test@123 |