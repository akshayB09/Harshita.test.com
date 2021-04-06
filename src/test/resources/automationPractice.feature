Feature: Order T-shirt and update personal details
  Scenario: Order T-Shirt and verify details
    Given User navigates to Website
    And User is logged in successfully
    When User select Category, subcategories and other details
      | category |  subcategory1 |  subcategory2 | itemName | quantity | size  |
      | WOMEN    |  Tops         |  T-shirt      | Faded Short Sleeve T-shirts | 2 | L |
      | WOMEN    |  Tops         |  T-shirt      | Faded Short Sleeve T-shirts | 5 | M |
    And User Clicks on Add to Cart button and user clicks on Proceed to Checkout button
    Then Order is placed successfully and verified n Order History



  Scenario: Update Personal details
    Given User navigates to Website
    And User is logged in successfully
    When User navigates to Personal Information and update First Name
    Then New name is updated and is reflected in Home Page

