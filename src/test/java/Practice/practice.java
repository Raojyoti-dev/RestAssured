package Practice;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class practice {
	@Test(priority=1)
	public void parsingXML() {
		given()
		
		.when()
			.get("https://mocktarget.apigee.net/xml")
		.then()
			.statusCode(200)
			.body("root.firstName",equalTo("John")) //here we are using xpath
			.body("root.lastName",equalTo("Doe"))
			.body("root.state",equalTo("CA"));
	
	}
	@Test(priority=2)
	public void parsingXMLMethod2() {
		Response res=given()
		
		.when()
			.get("https://mocktarget.apigee.net/xml");
			
		Assert.assertEquals(res.getStatusCode(),200);
		String name=res.xmlPath().get("root.firstName").toString();
		Assert.assertEquals(name,"John");
		
		
	}
	//@Test
	public void prasingXMLMethod3() {
		
		Response res=given()
		.when()
			.get("https://mocktarget.apigee.net/xml");
		Boolean status=false;
		XmlPath obj=new XmlPath(res.asString());
		
		List<String> list=obj.getList("root-city");
		
		for(String li:list) {
			if(li.equals("San Jose")) {
				status=true;
				break;
			}
		}
		
		Assert.assertEquals(status,true);
	}
	
	

}
