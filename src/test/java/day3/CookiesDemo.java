package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class CookiesDemo {

	//@Test(priority=1)
	public void testCookie() {
		
		given()
		
		.when()
		
			.get("https://www.google.com/")
			
		.then()
		.cookie("AEC","aasfgsegvs")  //checking cookie AEC value here
		.log().all();
			
		
	}
	
	@Test(priority=2)
	public void getCookiesInfo() {
		
		Response res=given()
		
		.when()
			.get("https://www.google.com/");
			
			//get single cookie info
			//String cookie_value=res.getCookie("AEC");
			//System.out.println("value is   "+cookie_value);
			
			//get all cookies info
			Map<String, String>cookies_values=res.getCookies();
			
			// System.out.println(cookies_values.keySet());
			//printing all cookies with key and values
			for(String k:cookies_values.keySet()) {
				
				String eachCookie=res.getCookie(k);
				System.out.println(k+"   "+eachCookie);
			}
		
	}
}
