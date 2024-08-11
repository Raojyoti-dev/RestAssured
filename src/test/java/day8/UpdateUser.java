package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class UpdateUser {
	@Test
	public void testUpdateUser(ITestContext context) {
		
		int id=(int) context.getAttribute("user_id");  //access test level variable
		//context.getSuite().getAttribute("user_id",id); //acess suit level variable
		
		Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		data.put("name",faker.name().fullName());
		data.put("gender","female");
		data.put("email",faker.internet().emailAddress());
		data.put("status","active");
		
		String bearerToken="token";
		
		given()
		
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("userId",id)
			.body(data.toString())
		
		.when()
		
			.put("https://gorest.co.in/public/v2/users/{userId}")
		.then()
			.statusCode(200)
			.log().all();
		
	}

}
