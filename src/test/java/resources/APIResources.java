package resources;
//enum is special class in java which has collection of methods or constants
public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetAplaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resource;

	
	APIResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource=resource;
	}
	
	public String getResource() {
		
		return resource;	
	}

}
