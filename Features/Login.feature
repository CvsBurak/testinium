Feature: Hepsiburada Shopping
  As a online shopper,
  I want to add items to my basket

  Background:
    Given the home page of hepsiburada is displayed

  Scenario Outline: Login to hepsiburada
    When the user clicks to login button
    Then the user on the login page
    When the user enters username "<username>" and password "<password>"
    And Clicks to login button
    Then User must be on home page and login text should change to "Hesabım"
    Then Close driver

    Examples:
      | username                    | password   |
      | burak-cavusoglu@hotmail.com | CvsBurak11 |