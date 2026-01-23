Feature: Add product to cart

  Scenario: Registered user completes a purchase
    Given the user is a registered user
    When the user logs in
    And adds a product to the cart
    Then the user checks out successfully


