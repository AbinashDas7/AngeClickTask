package getBookingID;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class getBookingIdStepDefinitions {
    private Response response;

    @Given("I have valid authentication credentials")
    public void iHaveValidAuthenticationCredentials() {
        RestAssured.authentication = RestAssured.basic("admin", "password123");
    }

    @When("I send a GET request to the booking API")
    public void iSendAGETRequestToTheBookingAPI() {
        response = given()
                .contentType("application/json")
                .when()
                .get("https://restful-booker.herokuapp.com/booking");
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        // Assert status code
        response.then().assertThat().statusCode(statusCode);
        response.then().log().all();
    }

    @Then("the response should contain booking IDs")
    public void theResponseShouldContainBookingIDs() {
        // Assert that the response body contains booking IDs
        response.then().assertThat().body("bookingid", hasItems(184, 289, 686, 522, 417, 216));
    }
}
