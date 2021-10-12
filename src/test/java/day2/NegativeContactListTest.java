package day2;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
public class NegativeContactListTest {
@Test
public void recordNotFound() {
	given()
	.when()
	.get("http://3.13.86.142:3000/contacts/5")
	.then()
	.log()
	.body().statusCode(404);
}

@Test(enabled=true,description="Adding Contact With Missing Parameter")
public void addingContactMissingParameter() {
JSONObject details=new JSONObject();
JSONObject loc=new JSONObject();
JSONObject emp=new JSONObject();
loc.put ("city","Mumbai");  
		loc.put ("country","India");	
		emp.put ("jobTitle","QA");
		emp.put ("company","LTI");
		details.put("firstName","");
		details.put("lastName","Shetty");
		details.put("email","Suraksha@lti.com");
		details.put("location",loc);
		details.put("employer",emp);
        String error	= given()
				.header("Content-Type","application/json")	
				.body(details.toJSONString())
				.when()
				   .post("http://3.13.86.142:3000/contacts")
                .then()
                	.log()
	                .body()
                	.statusCode(400)
		     	.extract()
		     	.path("err");
		Assert.assertTrue(error.contains("firstName: First Name is required"));	
}

@Test(enabled=true,description="Adding Contact With Too Many Size")
public void addingContactTooManySize() {
JSONObject details=new JSONObject();
JSONObject loc=new JSONObject();
JSONObject emp=new JSONObject();
loc.put ("city","MumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbai");  
		loc.put ("country","India");	
		emp.put ("jobTitle","QA");
		emp.put ("company","LTI");
		details.put("firstName","Suraksha");
		details.put("lastName","Shetty");
		details.put("email","Suraksha@lti.com");
		details.put("location",loc);
		details.put("employer",emp);
        String error	= given()
				.header("Content-Type","application/json")	
				.body(details.toJSONString())
				.when()
				   .post("http://3.13.86.142:3000/contacts")
                .then()
                	.log()
	                .body()
                	.statusCode(400)
		     	.extract()
		     	.path("err");
		Assert.assertTrue(error.contains("is longer than the maximum allowed length"));	
}


@Test(enabled=true,description="Adding Contact Verify Email")
public void addingContactVerifyEmail() {
JSONObject details=new JSONObject();
JSONObject loc=new JSONObject();
JSONObject emp=new JSONObject();
loc.put ("city","Mumbai");  
		loc.put ("country","India");	
		emp.put ("jobTitle","QA");
		emp.put ("company","LTI");
		details.put("firstName","Suraksha");
		details.put("lastName","Shetty");
		details.put("email","Surakshalti.com");
		details.put("location",loc);
		details.put("employer",emp);
        String error	= given()
				.header("Content-Type","application/json")	
				.body(details.toJSONString())
				.when()
				   .post("http://3.13.86.142:3000/contacts")
                .then()
                	.log()
	                .body()
                	.statusCode(400)
		     	.extract()
		     	.path("err");
		Assert.assertTrue(error.contains("Validator failed for path `email` with value `Surakshalti.com`"));	
}

@Test(enabled=true,description="Adding Contact With Invalid Character")
public void addingContactInvalidCharacter() {
JSONObject details=new JSONObject();
JSONObject loc=new JSONObject();
JSONObject emp=new JSONObject();
loc.put ("city","Mumbai");  
		loc.put ("country","India");	
		emp.put ("jobTitle","QA");
		emp.put ("company","LTI");
		details.put("firstName","Sur@k5h@");
		details.put("lastName","Shetty");
		details.put("email","Suraksha@lti.com");
		details.put("location",loc);
		details.put("employer",emp);
        String error	= given()
				.header("Content-Type","application/json")	
				.body(details.toJSONString())
				.when()
				   .post("http://3.13.86.142:3000/contacts")
                .then()
                	.log()
	                .body()
                	.statusCode(400)
		     	.extract()
		     	.path("err");
	Assert.assertTrue(error.contains("Validator failed for path `firstName` with value `Sur@k5h@`"));	
}
}
