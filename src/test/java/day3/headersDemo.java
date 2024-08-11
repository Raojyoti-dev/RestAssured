package day3;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class headersDemo {
	
	//@Test(priority=1) //headerDataValidation
	public void headerDataValidation() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
			
		.then()
					//("fieldname" , "value")
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.header("Server","gws")
			.log().all();
	}
	
	@Test(priority=2)
	public void getHeaderData() {
		
		Response res=given()
		 
		
		.when()
			.get("https://www.google.com/");
		
		//get single header info
		String header_value=res.getHeader("Server");
		System.out.println(header_value);
		
		//get all header info
		System.out.println("getting all header values...");
		Headers values=res.getHeaders();
		for(Header val:values) {
			System.out.println(val.getName()+"  "+val.getValue());
		}
		
		
	}

}
