Feature: Purchase Products with Total Amount Limit

  @TotalPurchaseBelowTheLimit
  Scenario: Total Purchase Below The Limit
    Given a user visits the pcfactory webpage
    And add two products to the cart
    When goes to the cart
    And the total of purchase is below the limit
    Then continue with the purchase

  @TotalPurchaseOverTheLimit
  Scenario: Total Purchase Over The Limit
    Given a user visits the webpage
    And add some products to the cart
    When visit the cart
    And the total of purchase is over the limit
    Then drop a product and continue with the purchase