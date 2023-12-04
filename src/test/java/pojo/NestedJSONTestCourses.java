package pojo;

import java.util.List;

public class NestedJSONTestCourses {

	// Now each of the JSON contains mini JSON so in this case again a separate
	// class need to be created for each JSON.
	// but again the WebAutomation JSON comes with the multiple array so in this
	// case we will use List.

	
	public List<CourseAPI> getApi() {
		return api;
	}

	public void setApi(List<CourseAPI> api) {
		this.api = api;
	}

	public List<CourseMobile> getMobile() {
		return mobile;
	}

	public void setMobile(List<CourseMobile> mobile) {
		this.mobile = mobile;
	}

	
	public List<CourseWebAutomation> getWebAutomation() {
		return webAutomation;
	}

	public void setWebAutomation(List<CourseWebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}

	private List <CourseWebAutomation> webAutomation;
	private List<CourseAPI> api;
	private List <CourseMobile> mobile;

}
