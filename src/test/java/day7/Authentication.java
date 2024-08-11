package day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.auth.AUTH;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class Authentication {
	
	//@Test(priority=1)
	public void BasicAuthentication() {
		
		given()
			.auth().basic("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true));
	}
	//@Test(priority=2)
	public void DigestAuthentication() {
		given()
			.auth().digest("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true));
	}
	//@Test(priority=3)
	public void PrimmptiveAuthentication() {
		given()
			.auth().preemptive().basic("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true));
	}

	//@Test(priority=4)
	public void BearerTokenAuthentication() {
		given()
			.headers("Authorization","Bearer "+"genetaredtoken")
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	//@Test(priority=5)
	public void testOAuth1Authentication() {  //this code will not work as this the only syntax
		given()  //this is for OAuth1.0 Authentication, this is the syntax
			.auth().oauth("consumerKey", "consumerSecrat", "acessToken", "tokenSecrate")
		.when()
			.get("url")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	//@Test(priority=6)
	public void OAuth2Authenitcation() {
		given()
		
			.auth().oauth2("token")  //here we are passing the token
		
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	@Test(priority=7)
	public void APIKeyAuthentication() {  //this code will not work as this the only syntax
		given()
			.queryParam("appid","apikeyvalue") // here we are passing api key and value as queryparams
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	

}
