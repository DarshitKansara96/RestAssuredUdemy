package pojo;

import static io.restassured.RestAssured.*;

public class SerializationDemo {

	private String message;
	private String greet;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getGreet() {
		return greet;
	}
	public void setGreet(String greet) {
		this.greet = greet;
	}
	
	public static void main(String[] args) {
		SerializationDemo sd = new SerializationDemo();
		sd.setMessage("Hello");
		sd.setGreet("Hi");
		
		// Serializing the request by providing java object to the request body.
		given().body(sd).when().post("/message").then().extract().response();
	}
}
