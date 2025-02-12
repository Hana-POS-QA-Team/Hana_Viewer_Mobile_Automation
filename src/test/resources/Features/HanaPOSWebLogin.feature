Feature: Login to POS Functionality

  Scenario Outline: Web Login Page Functionality
    Given Login to Hana POS Web App
    When User Enters the "<UserName>" and "<Password>" and click login
    Then User Should logged into Hana POS App

    Examples:
      | UserName   | Password |
      | hanauser_2 | 123      |