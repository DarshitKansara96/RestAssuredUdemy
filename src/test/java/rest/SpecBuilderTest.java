package rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GetAddPlaceSerialization;
import pojo.LocationSerialize;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class SpecBuilderTest {
	
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
		
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
		.addQueryParam("key", "qaclick123").build();
		
	//	In the next we are breaking the code.
		
		 RequestSpecification res =	given().spec(req).body(g);
		 
		 ResponseSpecification respec =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 
		Response response = res.when().post("/maps/api/place/add/json").then().log().all().spec(respec).extract().response();
		
		String getresponse = response.asString();
		System.out.println(getresponse);
		
	}

}
