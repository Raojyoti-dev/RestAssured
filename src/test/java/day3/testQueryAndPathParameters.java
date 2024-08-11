package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class testQueryAndPathParameters {
	
	@Test
	public void queryAndPathParams() {
		
		//https://reqres.in/api/users?page=2
		given()
			.pathParam("myPath","users") //pathparameter
			.queryParam("page",2)      //queryparameter
			
		
		.when()
			.get("https://reqres.in/api/{myPath}")
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
