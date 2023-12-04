package rest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class DynamicJSON {

	@Test(dataProvider="BooksData")
	public void Addbook(String name , String isbn , String asile) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().header("Content-Type" , "application/json").body(Payload.AddBook(name , isbn , asile)).
		when().post("/Library/Addbook.php")
		.then().log().all().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String ID = js.get("ID");
		System.out.println(ID);
		
	}
	
	@Test(dataProvider="BooksData")
	public void Deletebook(String name , String isbn , String asile) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().body(Payload.AddBook(name , isbn , asile)).when().delete("/Library/DeleteBook.php")
		.then().log().all().statusCode(200).extract().response();
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		return new Object[][] {{"Java" , "acba", "0210"} , {".Net" , "bcab" , "0120"} , {"C++" , "dbcd" , "2012"}};
		
	}
}
