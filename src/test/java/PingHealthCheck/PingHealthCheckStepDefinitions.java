package PingHealthCheck;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class PingHealthCheckStepDefinitions {

    private Response response;

    @Given("the API is accessible")
    public void theApiIsAccessible() {
        System.out.println("API SUccessfully accessible");
    }

    @When("I send a GET request to the endpoint")
    public void iSendAGetRequestToThePingEndpoint() {
        response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/ping");
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        response.then().assertThat().statusCode(expectedStatusCode);
    }

    @Then("the response body should be {string}")
    public void theResponseBodyShouldBe(String expectedBody) {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        Assert.assertEquals(responseBody, "Created");
    }
}
