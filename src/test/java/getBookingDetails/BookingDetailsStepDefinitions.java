package getBookingDetails;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingDetailsStepDefinitions {
    private Response response;
    private int bookingId;

    @Given("I have a booking ID")
    public void iHaveABookingID() {
        this.bookingId = 686;
    }

    @When("I retrieve the details of the booking")
    public void iRetrieveTheDetailsOfTheBooking() {
        response = given()
                .accept("application/json")
                .when()
                .get("https://restful-booker.herokuapp.com/booking/" + bookingId);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        response.then().assertThat().statusCode(expectedStatusCode);
        response.then().log().all();
    }

    @Then("the response should contain the booking details")
    public void theResponseShouldContainTheBookingDetails() {
        response.then().assertThat().body("firstname", notNullValue());
        response.then().assertThat().body("lastname", notNullValue());
        response.then().assertThat().body("totalprice", notNullValue());
        response.then().assertThat().body("depositpaid", notNullValue());
        response.then().assertThat().body("bookingdates.checkin", notNullValue());
        response.then().assertThat().body("bookingdates.checkout", notNullValue());
        response.then().assertThat().body("additionalneeds", notNullValue());
    }
}

