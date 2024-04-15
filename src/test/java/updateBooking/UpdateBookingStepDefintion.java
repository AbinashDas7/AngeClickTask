package updateBooking;


import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class UpdateBookingStepDefintion {
	
	private Response response;
	private Map<String, Object> bookingPayload;

	@Given("I have the booking ID for update")
	public void iHaveTheBookingID() {
		
		int bookingId = 628;
		System.out.println("Retrieved Booking ID: " + bookingId);
		
		bookingPayload = new HashMap<>();
		bookingPayload.put("firstname", "Anmol");
		bookingPayload.put("lastname", "Das");
		bookingPayload.put("totalprice", 111);
		bookingPayload.put("depositpaid", true);

		Map<String, String> bookingDates = new HashMap<>();
		bookingDates.put("checkin", "2024-01-01");
		bookingDates.put("checkout", "2024-04-01");

		bookingPayload.put("bookingdates", bookingDates);
		bookingPayload.put("additionalneeds", "Breakfast");

		System.out.println("Booking Payload: " + bookingPayload);
	}

	@When("I send a PUT request to update the booking")
	public void iSendAPUTRequestToUpdateTheBooking() {

		int bookingId = 628;
		String base64Credentials = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
		response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", base64Credentials)
				.body(bookingPayload)
				.when()
				.put("https://restful-booker.herokuapp.com/booking/" + bookingId);
	}

	@Then("the response status code should be {int}")
	public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
		response.then().assertThat().statusCode(expectedStatusCode);
	}

	@Then("the response should contain the updated booking details")
	public void theResponseShouldContainTheUpdatedBookingDetails() {
		// Verify the updated booking details in the response body
		response.then().assertThat().body("firstname", equalTo("Anmol"));
		response.then().assertThat().body("lastname", equalTo("Das"));
		response.then().assertThat().body("totalprice", equalTo(111));
		response.then().assertThat().body("depositpaid", equalTo(true));
		response.then().assertThat().body("bookingdates.checkin", equalTo("2024-01-01"));
		response.then().assertThat().body("bookingdates.checkout", equalTo("2024-04-01"));
		response.then().assertThat().body("additionalneeds", equalTo("Breakfast"));
	}
}