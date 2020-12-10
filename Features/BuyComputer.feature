Feature: trendyol Shopping
  As a online shopper,
  I want to add items to my basket

  Background:
    Given the home page of trendyol is displayed
    Then user must me on the home page

  Scenario: Login to trendyol
    When user writes "bilgisayar" on the search bar
    And clicks the search
    Then the user on the "bilgisayar" page
    When the user selects random product on the page
    Then user must be on the products page
    When user adds item to the basket
    Then basket count must increased one 
    When user clicks the basket and goes to basket page
    Then user must see the value of item price in the products page and the baskets page must be equal
    When user increase the product count to two
    Then item count must increased to two
    Then Close driver
