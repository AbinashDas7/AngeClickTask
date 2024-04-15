Feature: Retrieve Booking Details

  Scenario: Retrieve details of a specific booking
    Given I have a booking ID
    When I retrieve the details of the booking
    Then the response status code should be 200
    And the response should contain the booking details
