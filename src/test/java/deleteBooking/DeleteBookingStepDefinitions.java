package deleteBooking;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

public class DeleteBookingStepDefinitions {
    private Response response;
    private int bookingId;

    @Given("I have the booking ID")
    public void iHaveTheBookingID() {
        bookingId = 9;
    }

    @When("I send a DELETE request to delete the booking")
    public void iSendADELETERequestToDeleteTheBooking() {
        String base64Credentials = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
        response = given()
                .contentType("application/json")
                .header("Authorization", base64Credentials)
                .cookie("token", "abc123")
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/" + bookingId);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the response should contain a success message")
    public void theResponseShouldContainASuccessMessage() {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        Assert.assertEquals(responseBody, "Created");
    }

}
