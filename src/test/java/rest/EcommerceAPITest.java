package rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.EcommerceOrder;
import pojo.EcommerceOrderDetails;
import pojo.EcommerceViewDetailsPojoClass;
import pojo.EcommerceViewOrderDetailsData;
import pojo.LoginRequestTest;
import pojo.LoginResponseTest;

import static io.restassured.RestAssured.*;

@Test
public class EcommerceAPITest {

	public void EndtoEndEcommerceAPITest() throws Exception {

		RequestSpecification res = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		LoginRequestTest loginrequesttest = new LoginRequestTest();

		Properties p = new Properties();
		FileInputStream fi = new FileInputStream("config.properties");
		p.load(fi);

		String userEmail = p.getProperty("userEmail");
		loginrequesttest.setUserEmail(userEmail);
		String userPassword = p.getProperty("userPassword");
		loginrequesttest.setUserPassword(userPassword);

		RequestSpecification req = given().log().all().spec(res).body(loginrequesttest);

		LoginResponseTest response = req.when().post("/api/ecom/auth/login").then().log().all().extract().response()
				.as(LoginResponseTest.class);

		String token = response.getToken();
		String userId = response.getUserId();
		System.out.println(token);
		System.out.println(userId);

		// Add Product.

		RequestSpecification addproductbasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).build();

		RequestSpecification addproductreq = given().spec(addproductbasereq).param("productName", "Laptop")
				.param("productAddedBy", userId).param("productCategory", "Tech")
				.param("productSubCategory", "Software").param("productPrice", "20500")
				.param("productDescription", "HP").param("productFor", "IT Professional")
				.multiPart("productImage", new File(
						"C:\\Users\\MY PC\\Desktop\\RestAssuredDocuments\\43-434027_product-beauty-skin-care-personal-care-liquid-tree.png"));

		String addproductresponse = addproductreq.when().post("api/ecom/product/add-product").then().log().all()
				.extract().response().asString();

		JsonPath js = new JsonPath(addproductresponse);
		String productId = js.getString("productId");
		System.out.println(productId);

		// Create Order.

		RequestSpecification createorderbasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).addHeader("authorization", token).build();

		EcommerceOrderDetails od = new EcommerceOrderDetails();
		od.setCountry("India");
		od.setProductOrderedId(productId);

		List<EcommerceOrderDetails> orderdetailslist = new ArrayList();
		orderdetailslist.add(od);
		EcommerceOrder order = new EcommerceOrder();
		order.setOrders(orderdetailslist);

		RequestSpecification createorderreq = given().log().all().spec(createorderbasereq).body(order);

		String createOrderresponse = createorderreq.when().post("api/ecom/order/create-order").then().log().all()
				.extract().response().asString();

		System.out.println(createOrderresponse);

		/*
		 * // View Order Details.
		 * 
		 * RequestSpecification vieworderdetailsbasereq = new RequestSpecBuilder()
		 * .setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.
		 * JSON) .addHeader("authorization", token).build();
		 * 
		 * RequestSpecification vieworderdetailsreq =
		 * given().spec(vieworderdetailsbasereq).queryParam("id", productId);
		 * 
		 * EcommerceViewDetailsPojoClass vod =
		 * vieworderdetailsreq.when().get("api/ecom/order/get-orders-details").then().
		 * log().all().extract().response() .as(EcommerceViewDetailsPojoClass.class);
		 * 
		 * System.out.println(vod.getData().getOrderBy());
		 * System.out.println(vod.getMessage());
		 */


		// Delete the product.

		RequestSpecification deleteproductbasereq = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token)
				.setContentType(ContentType.JSON).build();

		RequestSpecification deleteproductreq = given().relaxedHTTPSValidation().log().all().spec(deleteproductbasereq)
				.pathParam("productId", productId);

		String deleteproductresponse = deleteproductreq.when().delete("api/ecom/product/delete-product/{productId}")
				.then().log().all().extract().response().asString();

		JsonPath jsdelete = new JsonPath(deleteproductresponse);
		Assert.assertEquals("Product Deleted Successfully", jsdelete.getString("message"));
	}

}
