package day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse { 
	
	//@Test(priority=1)
	public void parsingXML() {
		given()
		
		.when()
			.get("https://mocktarget.apigee.net/xml")
		.then()
			.statusCode(200)
			//.header("Content-Type","application/xml; charset-utf-8")
			.body("root.firstName",equalTo("John"))  //we are specifiying xpath for the field and expected value
			.body("root.state",equalTo("CA"));  //root.firstName is the xpath for the field firstName and we are comparing it with actual value
		//to get a particular field value in XML response we need to give xpath for that particular element. in xpath here we are using . instead of / to traverse to the element.
		
	}
	
	//@Test(priority=2)
	public void parsingXMLMethod2() {
		Response  res=
				given()
				
				.when()
					.get("https://mocktarget.apigee.net/xml");
		
				Assert.assertEquals(res.getStatusCode(),200);
				String headerval=res.getHeader("ContentType");
				System.out.println(headerval);
				String name=res.xmlPath().get("root.lastName").toString(); //here in get method we are giving xpath for that field instead of only field name
				Assert.assertEquals(name,"Doe");
	}
	
	//validating city name is present in the response body or not even if it present dynamicallay out of all fields
	@Test(priority=3)
	public void parsingXMLMethod3() {
		
		Response res=given()
		
		.when()
			.get("https://mocktarget.apigee.net/xml");
		
		Boolean status=false;
		XmlPath xmlobj=new XmlPath(res.asString());
		//verify total no. of citys //this line will not work as response doesnt have multiple city names but code should written like this
		List<String> list=xmlobj.getList("root.city");
		
		//verify if that city name is in the response or not out of all city names
		for(String li:list) {
			if(li.equals("San Jose")) {
				status=true;
				break;
			}
		}
		
		Assert.assertEquals(status,true);
		
		
		
	}

}
