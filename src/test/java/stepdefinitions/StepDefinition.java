package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Request;

import DataOrganizer.EnumPathParamResources;
import DataOrganizer.TestDataBuilder;
import DataOrganizer.Util;

import static org.junit.Assert.*;
import POJO.Location;
import POJO.maps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Util {
	RequestSpecification reqspec, req1;
	ResponseSpecification resspec;
	Response response;
	String str;
	JsonPath js;
	String placeId;
	EnumPathParamResources em;
	TestDataBuilder databuilder = new TestDataBuilder();

	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		// sending the request and capturing the request
		req1 = given().spec(genericRequestSpec()).body(databuilder.addplacepayload(name, language, address));
		
		
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resourse, String httpMethod) {
		 em = EnumPathParamResources.valueOf(resourse);

		if (httpMethod.equalsIgnoreCase("POST")) {
			response = req1.when().post(em.getResource())
					.then().spec(genericResponseSpec()).extract().response();
		} 
		else if (httpMethod.equalsIgnoreCase("DELETE")) {
			response = req1.when().delete(em.getResource())
					.then().spec(genericResponseSpec()).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("GET")) {
			response = req1.when().get(em.getResource())
					.then().spec(genericResponseSpec()).extract().response();
		}

	}

	@Then("place added successfully with status code {int}")
	public void place_added_successfully_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expValue) {
		str = response.asString();
		js = new JsonPath(str);
		assertEquals(js.get(key).toString(), expValue);
		
	}
	
	@Then("verify the place_Id created for {string} using {string}")
	public void verify_the_place_Id_created_for_using(String s,String k) {
	/*	placeId=getValue(response,"place_id");
	
		given().spec(reqspec).queryParam("place_id", placeId)
		.when().get(em.getResource());*/
		
	}

}
