Feature: Partial Update Booking

  Scenario: Update a current booking with a partial payload
    Given I have the booking ID
    When I send a PATCH request to update the booking with partial payload
    Then the response status code should be 200
    And the response should contain the updated booking details
