package day6;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class JsonXMLSchemaValidator {
	
	@Test
	public void testJsonSchema() {
		
		given() 
			
		.when()
			.get("http://localhost:3000/students")
		.then()
		//this will validate all field types instead of indiviusal fileds
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemavalidator.json"));
	}

}
