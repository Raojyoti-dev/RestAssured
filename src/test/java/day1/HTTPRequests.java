package day1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class HTTPRequests {
	
	
	int id;  //varibale to store fetched ID from json response
	
	 @Test (priority=1)   //get Request
	public void getUsers() {
		 
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();		
	}
	
	@Test (priority=2)  //Post/cREATE Request
	public void createUser() {
		
		HashMap data=new HashMap();
		data.put("name","morpheus");
		data.put("job","leader");
		
		id=given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");  //here from Response i am fetching id to use in put request.
		
		//.then()
			//.statusCode(201)
			//.log().all();
		
	}
	
	@Test (priority=3, dependsOnMethods = {"createUser"})
	public void updateUser() {
		
		HashMap data=new HashMap();
		data.put("name","morpheus");
		data.put("job","zion resident");
		
		given()
		
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority=4)
	public void deleteUser() {
		
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
	}

	

	

	


}
