package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	RequestSpecification spec;
	
	@BeforeMethod
	protected void setup() {
		spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
	}
	
	protected static Response createBooking() {
		//Create json body
		JSONObject body = new JSONObject();
		body.put("firstname", "Ben");
		body.put("lastname", "Tenison");
		body.put("totalprice", 1599);
		body.put("depositpaid", true);
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2013-02-21");
		bookingdates.put("checkout", "2020-03-27");
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Dinner");
		//Get response
		Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString()).post("https://restful-booker.herokuapp.com/booking");
		return response;
		
	}
	public static void booking() throws Exception {
		if(true) {
		throw new Exception();
		}
		System.out.println("Booking");
	}

}
