package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class DiffWaysToCreatePostRequestBody {
	//Post request using hashmap
	@Test(priority=1)  
	public void testPostUsingHashMap() {
		
		//created hashmap to store data
		HashMap data=new HashMap();
		data.put("name","jyoti");
		data.put("location","india");
		data.put("phone","2345");
		 String course[]={"java","javascript"};
		data.put("courses",course);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("jyoti"))   //validate from response json
			.body("location",equalTo("india"))
			.body("phone",equalTo("2345"))
			.body("courses[0]",equalTo("java"))
			.body("courses[1]",equalTo("javascript"))
			//.header("contentType","application/json;charset=utf-8") 
			.log().all();
		
	}
	//post request using org.json libray
	//@Test(priority=2)
	public void testPostRequestUsingJsonLibrary() {
		
		//created json obj to store data
		JSONObject data=new JSONObject();
		data.put("name","Shiva");
		data.put("location","USA");
		data.put("phone","12345");
		String arr[]= {"C","C++"};
		data.put("courses",arr);
		
		given()
			.contentType("application/json")
			.body(data.toString())  //while sending data convert to string
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Shiva"))   
			.body("location",equalTo("USA"))
			.body("phone",equalTo("12345"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.log().all();
	}
	
	
	
	//post request using pojo class
	//@Test(priority=3)
	public void testPostRequestUsingPOJOClass() {
		//using pojo class object storing the value
		PostRequest_POJOClass data=new PostRequest_POJOClass();
		data.setName("Prakash");
		data.setLocation("Hyderabad");
		data.setPhone("9999");
		String course[]= {"a","b"};
		data.setCourses(course);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Prakash"))   
			.body("location",equalTo("Hyderabad"))
			.body("phone",equalTo("9999"))
			.body("courses[0]",equalTo("a"))
			.body("courses[1]",equalTo("b"))
			.log().all();
	}
	
	@Test(priority=4)
	public void testPostRequestUsingExternalJSON() throws FileNotFoundException {
		
		File f=new File(".\\body.json");
		FileReader fr=new FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data=new JSONObject(jt);
		
		
		given()
			.contentType("application/json")
			.body(data.toString())  //while sending data convert to string
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name",equalTo("Harsh"))   
			.body("location",equalTo("USA"))
			.body("phone",equalTo("12345"))
			.body("courses[0]",equalTo("a"))
			.body("courses[1]",equalTo("b"))
			.log().all();
	}
	
	@Test(priority=5)
	public void testDelete() {
		given()
		
		.when()
		.delete("http://localhost:3000/students/b236")
		.then()
		.statusCode(200);
		
	}
	
}
