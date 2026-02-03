Feature: Add product to cart

  Scenario: user completes a Registeration
    Given the user is a registered user

  Scenario: Registered user completes a purchase
    When the user logs in
    And adds a product to the cart
    Then the user checks out successfully