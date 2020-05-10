package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//execute this code only when place id is null
		//write a code that will give place id
		
		StepDefination m = new StepDefination();
		if(m.place_id==null) {
		m.add_place_payload_with("vijay", "English", "Maharashtra, India");
		m.user_call_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_map_to_using("vijay", "GetAplaceAPI");
		
		}
	}

}
