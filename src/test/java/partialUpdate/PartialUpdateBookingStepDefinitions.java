package partialUpdate;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PartialUpdateBookingStepDefinitions {
    private Response response;
    private Map<String, Object> bookingPayload;

    @Given("I have the booking ID")
    public void iHaveTheBookingID() {
        int bookingId = 628;
        System.out.println("Retrieved booking ID: " + bookingId);
    }

    @When("I send a PATCH request to update the booking with partial payload")
    public void iSendAPATCHRequestToUpdateTheBookingWithPartialPayload() {
        int bookingId = 628;
        String base64Credentials = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
        bookingPayload = new HashMap<>();
        bookingPayload.put("firstname", "Abinash");
        bookingPayload.put("lastname", "Dash");

        response = given()
        		.contentType(ContentType.JSON)
                .header("Authorization", base64Credentials)
                .body(bookingPayload)
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/" + bookingId);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        response.then().assertThat().statusCode(expectedStatusCode);
        response.then().log().all();
    }

    @Then("the response should contain the updated booking details")
    public void theResponseShouldContainTheUpdatedBookingDetails() {
    	 response.then().assertThat().body("firstname", equalTo("Abinash"));
    	    response.then().assertThat().body("lastname", equalTo("Dash"));
    }
}