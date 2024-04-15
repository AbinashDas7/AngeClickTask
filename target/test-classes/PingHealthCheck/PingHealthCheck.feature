Feature: Ping Health Check
  As a user
  I want to check the health status of the API
  So that I can confirm whether it's up and running

  Scenario: Verify Ping Health Check
    Given the API is accessible
    When I send a GET request to the endpoint
    Then the response status code should be 201
    And the response body should be "OK"
