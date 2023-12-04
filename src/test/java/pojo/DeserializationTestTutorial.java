package pojo;

public class DeserializationTestTutorial {
	
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public NestedJSONTestCourses getCourses() {
		return courses;
	}
	public void setCourses(NestedJSONTestCourses courses) {
		this.courses = courses;
	}
	
	private String instructor;
	private String services;
	private String expertise;
	// private String courses;
	// Since courses is a nested JSON thus we are calling this JSON in a separate class and then connecting it with the main class 
	//by setting the return type of this JSON as sub class name instead of String.
	private NestedJSONTestCourses courses;
	private String linkedIn;
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

}
