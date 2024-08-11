package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FakerDataGenerator {

 @Test
 public void testGeneratorDummyData() {
	 
	 Faker faker=new Faker();
	 
	 String firstName=faker.name().firstName();
	 String lastName=faker.name().lastName();
	 
	 String passwrod=faker.internet().password();
	 String phoneno=faker.phoneNumber().phoneNumber();
	 
	 System.out.println(firstName);
	 System.out.println(lastName);
	 System.out.println(passwrod);
	 System.out.println(phoneno);
 }
	
	

}
