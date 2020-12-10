Feature: trendyol Shopping
  As a online shopper,
  I want to login to the trendyol

  Background:
    Given the home page of trendyol is displayed
    Then user must me on the home page

  Scenario Outline: Login to trendyol
    When the user clicks to login button
    Then the user on the login page
    When the user enters username "<username>" and password "<password>"
    And Clicks to login button
    Then User must be on home page and login text should change to "Hesabım"
    Then Close driver

    Examples:
      | username                    | password   |
      | burak-cavusoglu@hotmail.com | 159753brk |