Feature: Delete a Booking

  Scenario: Delete a booking by ID
    Given I have the booking ID
    When I send a DELETE request to delete the booking
    Then the response status code should be 201
    And the response should contain a success message