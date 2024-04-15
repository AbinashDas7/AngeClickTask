Feature: Create a new booking

  Scenario: Create a new booking in the API
    Given I have a booking payload
    When I send a POST request to create a booking
    Then the response status code should be 200
    And the response should contain the booking details
    And the response should contain a booking ID
