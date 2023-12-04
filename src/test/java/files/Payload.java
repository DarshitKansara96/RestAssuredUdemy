package files;

import java.util.ArrayList;
import java.util.HashMap;

import resources.DataDriven;

public class Payload {
	
	public static String AddBook(String name , String isbn, String aisle) {
		String addbook = "{\r\n"
				+ "    \"name\": \""+name+"\",\r\n"
				+ "    \"isbn\": \""+isbn+"\",\r\n"
				+ "    \"aisle\": \""+aisle+"\",\r\n"
				+ "    \"author\": \"John foer\"\r\n"
				+ "}";
		return addbook;
	}
	
	public static HashMap latestAddbook() throws Exception {
		
		DataDriven d = new DataDriven();
		ArrayList data = d.getData("RestAssuredTest" , "RestAssured");
		
		HashMap <String , Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("name", data.get(1));
		jsonmap.put("isbn" , data.get(2));
		jsonmap.put("aisle" , data.get(3));
		jsonmap.put("author" , data.get(4));
		
		/*
		 * HashMap <String , Object> nestedmap = new HashMap<String, Object>();
		 * jsonmap.put("lat", "123"); jsonmap.put("long", "12345");
		 * jsonmap.put("location", nestedmap);
		 */
		
		return jsonmap;
	}

}
