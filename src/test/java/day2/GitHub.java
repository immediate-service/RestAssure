package day2;


import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;
public class GitHub {
	@BeforeTest
	public void beforeTest() {
		RestAssured.baseURI="https://api.github.com/user/repos";
	    authentication=oauth2("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");
	}
  @Test(enabled=true)
  public void gettingAllRepositories() {
 given()
     
      .when()
        .get()
      .then()
        .log()
        .body()
        .statusCode(200);
  
  }
  
  @Test(enabled=true)
  public void createRepositories() {
 JSONObject data=new JSONObject();
 data.put("name", "RestAssuredCreations4");
 data.put("description", "I am created By RestAssured Tool");
 data.put("homepage", "https://github.com/immediate-service");
	  given()
        
     .header("Content-Type","application/json")
     .body(data.toJSONString())  
        .when()
        .post()
      .then()
        .log()
        //.headers()
        .body()
        .statusCode(201)
 .time(Matchers.lessThan(8000L), TimeUnit.MILLISECONDS);
  }

}
