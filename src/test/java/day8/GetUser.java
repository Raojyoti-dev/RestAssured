package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetUser {
	@Test
	public void testGetUser(ITestContext context) {
		
		int id =(int) context.getAttribute("user_id"); //acess test level variable
		//context.getSuite().getAttribute("user_id",id);  ////acess suit level variable
		String bearerToken="generatedToken";
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("userId", id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{userId}")
		.then()
			.statusCode(200)
			.log().all();
	}

}
