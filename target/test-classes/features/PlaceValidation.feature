

Feature: Validating place API

@AddPlace @Regression
Scenario Outline: Verify if place added successfully
  	Given Add place Payload with "<name>" "<language>" "<address>"
  	When user calls "AddplaceAPI" with "post" http request 
  	Then place added successfully with status code 200
  	And "status" in response body is "OK"
  	And "scope" in response body is "APP"
  	And verify the place_Id created for "<name>" using "getPlaceAPI"
  	
Examples: 
  | name  | language | address   |
  | amma  | Bengali  | Kolkata   |
  #| nanna | Telugu   | Kodur     |
      


