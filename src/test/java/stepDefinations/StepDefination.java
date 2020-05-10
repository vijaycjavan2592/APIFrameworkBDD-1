package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
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
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	RequestSpecification resp;
	ResponseSpecification respSpec;
	Response response;
	static String place_id;
	JsonPath js;

	TestDataBuild data = new TestDataBuild();
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
				
		resp = given().spec(requestSpecification())
		.body(data.addPlacePayload(name,language,address));
	}

	@When("user call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String httpMethod) {
	  
	//constructor will call with value of resources which you pass	
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();		
		
		if(httpMethod.equalsIgnoreCase("Post"))
		response = resp.when().post(resourceAPI.getResource());
				//.then().spec(respSpec).extract().response();
		else if(httpMethod.equalsIgnoreCase("GET"))
			response = resp.when().get(resourceAPI.getResource());
	  
	}

	@Then("then API call is sucess with status code {int}")
	public void then_API_call_is_sucess_with_status_code(Integer int1) {
	   
	    assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    		
		assertEquals(getJsonPath(response,keyValue),expectedValue);
	 
	}

	@Then("Verify place_id created map to {string} using {string}")
	public void verify_place_id_created_map_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	
		place_id = getJsonPath(response,"place_id");
		resp = given().spec(requestSpecification()).queryParam("place_id", place_id);

		user_call_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
			
	}
	//*************************Delete *********************
	@Given("Deleteplace payload")
	public void deleteplace_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	
	    resp = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	    
	   
	   
	    
	}



}
