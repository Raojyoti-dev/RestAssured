package day8;

import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	@Test
	public void testDeleteuser(ITestContext context) {
		
		int id=(int) context.getAttribute("user_id"); //access test level varibale
		//context.getSuite().getAttribute("user_id",id); //acess suit level variable
		
		String bearerToken="token";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("userId",id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{userId}")
		.then()
			.statusCode(204)
			.log().all();
		
	}

}
