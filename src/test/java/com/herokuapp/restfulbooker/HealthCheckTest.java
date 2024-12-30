package com.herokuapp.restfulbooker;


import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HealthCheckTest extends BaseTest {
	
	@Test
	public void FirstHealthCheckTest() {
		
		
		given().
			spec(spec).
		when().
			get("/ping").
		then().
			assertThat().statusCode(201);
		
	}
	

	@Test
	public void HeadersAndCookiesTest() {
		
		Header someHeader = new Header("some_name", "Some_value");
		spec.header(someHeader);
		
		Cookie someCookie = new Cookie.Builder("some cookie", "some cookie value").build();
		spec.cookie(someCookie);
		
		
		Response response = RestAssured.given(spec).
				cookie("Test cookie name", "Test cookie value").
				header("Test header name", "Test header Value").log().all().get("/ping");
		
		//Get Headers
		Headers headers = response.getHeaders();
		System.out.println("Headers : "+headers);
		
		//Particular Header
		Header serverHeader1 = headers.get("Server"); 
		System.out.println(serverHeader1.getName()+" : "+serverHeader1.getValue());
		
		String serverHeader2 = response.getHeader("Server");
		System.out.println("Server : "+serverHeader2);
		//Get Cookies
		Cookies cookie = response.getDetailedCookies();
		System.out.println("Cookies : "+cookie);
		
		
	}


}
