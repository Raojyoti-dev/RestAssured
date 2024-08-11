package day4;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PrasingJSONResponseData {
	
	//@Test(priority=1) 
	public void PrasingJSONResponse() {
		//Approach 1
		given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/students")
		
		.then()
			.statusCode(200)
			.header("Content-Type","application/json");
			//.body("students[2].name",equalTo("jack"));  //here we are using json path finder for name feiled of particular json object 
	}
	
	//Approach 2
	//@Test(priority=2)
	public void ParsingJSONResponseDataMethod2() {
		
		Response res=given()
		
		.contentType(ContentType.JSON)
		.when()
		.get("http://localhost:3000/students");
		
		Assert.assertEquals(res.getStatusCode(),200);  //validations
		//Assert.assertEquals(res.header("ContentType"),"application/json;charset=utf-8");
		
		String name=res.jsonPath().get("students[2].name").toString();  ////here we are using json path finder for name feiled of particular json object and get method will return objct hence converting to string
		System.out.println(name);
		Assert.assertEquals(name,"jack");
	}
	
	@Test(priority=3)  //validating a particular name field in the response body irrespective of the order of objects in the response body
	public void PrasingJSONResopnseToValidateDataDynamically() {
		
		Response res=given()
		
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/students");
		//irrespective of json objects in response validatinfg any field using json objcet class
		JSONObject js=new JSONObject(res.asString());  //converting reposne to json object
		
		//print all the names
		boolean status=false;
		for(int i=0;i<js.getJSONArray("students").length();i++) {
			
			String name=js.getJSONArray("students").getJSONObject(i).get("name").toString();
			System.out.println(name);
			if(name.equals("jack")) {
				status=true;
				break;
			}
		}
		
		Assert.assertEquals(status,true);
		
		
		//sum of all phonenumbers
		 double sum=0.00;
		 for(int i=0;i<js.getJSONArray("students").length();i++) {
			 
			 String num=js.getJSONArray("students").getJSONObject(i).getString("number").toString();
			 sum=sum+Double.parseDouble(num);
		 }
		 
		 System.out.println(sum);
		
		
		
	}

}
