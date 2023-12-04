package pojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class SerializationTestTutorial {
	
	@Test
	public void AddPlace() {
		
		GetAddPlaceSerialization g = new GetAddPlaceSerialization();
		g.setAccuracy(30);
		g.setAddress("Geeta Nagar , Bageshree CHS LTD");
		g.setLanguage("English");
		g.setName("Darshit");
		g.setPhone_number("12345689");
		g.setWebsite("www.dummy.com");
		
		List <String> mylist = new ArrayList <String>();
		mylist.add("Shoes");
		mylist.add("Shop");
		g.setTypes(mylist);
		
		LocationSerialize l = new LocationSerialize();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		g.setLocation(l);
		;
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		 Response response =	given().queryParam("key", "qaclick123").body(g).
		when().post("/maps/api/place/add/json").then().statusCode(200).extract().response();
		
		String getresponse = response.asString();
		System.out.println(getresponse);
		
	}

}
