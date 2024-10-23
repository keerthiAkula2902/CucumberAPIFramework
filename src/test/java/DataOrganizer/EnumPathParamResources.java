package DataOrganizer;

public enum EnumPathParamResources {
	
	AddplaceAPI("maps/api/place/add/json"),
	getPlaceAPI("maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");
	
	private String resource;
	
	EnumPathParamResources(String resource){
		this.resource=resource;
		
	}
	
	public String getResource() {
		return resource;
	}

}
