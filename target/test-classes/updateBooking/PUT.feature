Feature: Update Booking

  Scenario: Update an existing booking
    Given I have the booking ID for update
    When I send a PUT request to update the booking
    Then the response status code should be 200
    And the response should contain the updated booking details
