package resources;
import java.util.ArrayList;

public class TestCaseSample {
	
	public static void main(String[] args) throws Exception {
		
		DataDriven d = new DataDriven();
		
		ArrayList data = d.getData("AddProfile" , "TestData");
		
		System.out.println(data);
	}

}
