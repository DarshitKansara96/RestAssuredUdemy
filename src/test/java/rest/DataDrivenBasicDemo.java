package rest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import files.Payload;

public class DataDrivenBasicDemo {
	
	public static String ID;

	@Test(priority = 1)
	public void AddBook() throws Exception {
		

		RestAssured.baseURI = "http://216.10.245.166";
		String addbookresponse =  given().log().all().header("Content-Type", "application/json").body(Payload.latestAddbook())
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath j = new JsonPath(addbookresponse);
		 ID = j.get("ID");
		System.out.println(ID);
	}
	
	@Test(priority = 2)
	public void DeleteBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		given().log().all().header("Content-Type", "application/json").
		body(ID).when().post("/Library/DeleteBook.php").then().log().all().assertThat().statusCode(200);
	}

}
