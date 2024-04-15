package createNewBooking;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateBookingStepDefinitions {
    private Response response;
    private Map<String, Object> bookingPayload;

    @Given("I have a booking payload")
    public void iHaveABookingPayload() {
        bookingPayload = new HashMap<>();
        bookingPayload.put("firstname", "Jim");
        bookingPayload.put("lastname", "Brown");
        bookingPayload.put("totalprice", 111);
        bookingPayload.put("depositpaid", true);

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        bookingPayload.put("bookingdates", bookingDates);
        bookingPayload.put("additionalneeds", "Breakfast");
        
        System.out.println(bookingPayload);
    }

    @When("I send a POST request to create a booking")
    public void iSendAPOSTRequestToCreateABooking() {
        response = given()
                .contentType(ContentType.JSON)
                
                .body(bookingPayload)
                .when()
                .post("https://restful-booker.herokuapp.com/booking");
        int bookingId = response.then().extract().path("bookingid");
        System.out.println("Generated Booking ID: " + bookingId);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        response.then().assertThat().statusCode(expectedStatusCode);
    }

    @Then("the response should contain the booking details")
    public void theResponseShouldContainTheBookingDetails() {
        response.then().assertThat().body("booking.firstname", equalTo("Jim"));
        response.then().assertThat().body("booking.lastname", equalTo("Brown"));
        response.then().assertThat().body("booking.totalprice", equalTo(111));
        response.then().assertThat().body("booking.depositpaid", equalTo(true));
        response.then().assertThat().body("booking.bookingdates.checkin", equalTo("2018-01-01"));
        response.then().assertThat().body("booking.bookingdates.checkout", equalTo("2019-01-01"));
        response.then().assertThat().body("booking.additionalneeds", equalTo("Breakfast"));
    }

    @Then("the response should contain a booking ID")
    public void theResponseShouldContainABookingID() {
        response.then().assertThat().body("bookingid", notNullValue());
    }
}

