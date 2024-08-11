package day6;


import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;


public class XMLSchemaValidator {
	@Test
	public void smlSchemaValidation() {
		
		given()
		
		.when()
		  .get("https://mocktarget.apigee.net/xml")
		.then()
				//this will validate all field types instead of indiviusal fileds
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlschemavalidation.xsd"));
	}

}
