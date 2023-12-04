package rest;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.CourseAPI;
import pojo.CourseWebAutomation;
import pojo.DeserializationTestTutorial;

public class OAuthTest {

	@Test
	public void AuthorizationTest() {

		String getcodeURL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWgavdcd16BpCjBU1LfgQN4Vos_J6Pb3Z3JYgVYNBrz2jvxA8SJWMUkMRSAw6y-9zQufew&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";

		String[] webAutomationcoursetitles = {"Selenium Webdriver Java" , "Cypress" , "Protractor"};
		String split1 = getcodeURL.split("code=")[1];
		String code = split1.split("&scope")[0];
		System.out.println(code);

		String accesstoken = given().header("Content-Type", "application/json").urlEncodingEnabled(false)
				.queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		System.out.println(accesstoken);

		JsonPath js = new JsonPath(accesstoken);
		String getaccesstoken = js.getString("access_token");
		System.out.println(getaccesstoken);

		DeserializationTestTutorial d = given().queryParam("access_token", getaccesstoken).expect()
				.defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php")
				.as(DeserializationTestTutorial.class);

		System.out.println(d.getLinkedIn());
		System.out.println(d.getInstructor());
		
		List<CourseAPI> apicourses = d.getCourses().getApi();
		for(int i=0;i<apicourses.size();i++) {
			if(apicourses.get(i).getCourseTitle().equals("SoapUI Webservices testing")) {
				System.out.println(apicourses.get(i).getPrice());
			}
		}
		
		ArrayList <String> a = new ArrayList();
		List<CourseWebAutomation> wc = d.getCourses().getWebAutomation();
		
		for(int j=0 ; j<wc.size();j++) {
			//adding the coursetitle values to ArrayList.
			a.add(wc.get(j).getCourseTitle());
		}
		
		// We need to convert webAutomationcoursetitles array into an ArrayList such that we can compare it with ArrayList a;
		
		List expected = Arrays.asList(webAutomationcoursetitles);
		
		// We will perform assert to check whether the actual arraylist matches with the expected arraylist.
		
		Assert.assertTrue(a.equals(expected));
	
	}
		

}
