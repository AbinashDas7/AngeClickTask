Feature: Booking API Tests

  Scenario: Retrieve booking IDs with valid authentication
    Given I have valid authentication credentials
    When I send a GET request to the booking API
    Then the response status code should be 200
    
