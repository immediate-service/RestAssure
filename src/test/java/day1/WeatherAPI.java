package day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
public class WeatherAPI {
  @Test(enabled =false, description="Getting weather information of Specific City")
  public void getWeather() {
	  RestAssured.given()//some  Pre-condition like authentication
	            .when() //Performs some steps
	                .get("https://api.openweathermap.org/data/2.5/weather?q=mangalore&appid=928a61197f2183be1caa244db12d6001")
	            .then()//Some Post-Condition like Verification
	                .log() //print data in console 
                    .body()
                    .statusCode(200);
  
  }
  @Test(description="Getting weather information of Specific City")
  public void getWeather2() {
	  Response res=RestAssured.given()//some  Pre-condition like authentication
	                 .when() //Performs some steps
	                 .get("https://api.openweathermap.org/data/2.5/weather?q=mangalore&appid=928a61197f2183be1caa244db12d6001");
	  
	  System.out.println(res.prettyPrint());        
	  System.out.println(res.getTime());        
	  System.out.println(res.getStatusCode());        
	  System.out.println(res.getContentType());        
  Assert.assertEquals(res.getStatusCode(),200);
  }
  
  @Test(enabled=false ,description="Getting weather information of Specific City")
  public void getWeather3() {
	  RestAssured.given()//some  Pre-condition like authentication
	                .queryParam("q", "mangalore")
	                .queryParam("appid", "928a61197f2183be1caa244db12d6001")
	  .when() //Performs some steps
	                 .get("https://api.openweathermap.org/data/2.5/weather")
	   .then()//Some Post-Condition like Verification
      .log() //print data in console 
      .body()
      .statusCode(200);
	  
  }
  
  @Test(enabled =true,description="Getting weather information of Specific City")
  public void getWeather4() {
	Map<String,String> param=new HashMap<String,String>();
	  param.put("q", "mangalore");
     param.put("appid", "928a61197f2183be1caa244db12d6001");
	  RestAssured.given()//some  Pre-condition like authentication
	             .queryParams(param)   
			  .when() //Performs some steps
	                 .get("https://api.openweathermap.org/data/2.5/weather")
	  .then()//Some Post-Condition like Verification
      .log() //print data in console 
      .body()
      .statusCode(200);
	 
  }
}
