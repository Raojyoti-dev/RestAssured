package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class CreateUser {
	@Test
	public void TestCreateUser(ITestContext context) { // here ITestContext is interface which will set generated if  to a global variable
		int id;
		Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		data.put("name",faker.name().fullName());
		data.put("gender","male");
		data.put("email",faker.internet().emailAddress());
		data.put("status","inactive");
		
		String bearerToken="generated token";
		
		id=given()
		
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
		
		.when()
		
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
			context.setAttribute("user_id",id); // here variable is vailable at test level only , hence if we run tests indivisually under same suit , it will not be able to access for 2nd tests onwards,only first st will be executed
			//context.getSuite().setAttribute("user_id",id); // here the variable is available at suit level so that we can run tests in  indivisuallly under same suit
		
	}

	

}
