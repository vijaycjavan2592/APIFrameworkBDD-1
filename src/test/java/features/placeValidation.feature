Feature: Validating place API's

@AddPlace @Regression
  Scenario Outline: Verify if palce is being Sucessfully added using AddPlaceAPI
    Given Add place payload with "<name>" "<language>" "<address>"
    When user call "AddPlaceAPI" with "Post" http request
    Then then API call is sucess with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_id created map to "<name>" using "GetAplaceAPI"

    Examples: 
      | name    | language | address            |
      | AAhouse | English  | world cross center |
  #   | BBhouse | Hindi    | Sea cross center   |
  
@DeletePlace @Regression
  Scenario: Verify if Delete place functionality is working
    Given Deleteplace payload
    When user call "DeletePlaceAPI" with "Post" http request
    Then then API call is sucess with status code 200
    And "status" in response body is "OK"
