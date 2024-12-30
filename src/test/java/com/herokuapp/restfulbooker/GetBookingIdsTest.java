package com.herokuapp.restfulbooker;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBookingIdsTest extends BaseTest {
	
	@Test
	public void getBookingIdsWithoutFilter() {
		
		//Get response with booking ids
		Response response = RestAssured.given().spec(spec).get("/booking");
		response.print();
		
		//Verify the response
		Assert.assertEquals(response.getStatusCode(),200,"Status code should be 200. but its different");
		
		//Verify atleast one booking id is in response
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(),"List of booking Ids is empty, but it shouldn't be");
		
	}
	
	@Test
	public void getBookingIdsWithFilter() {
		
		spec.queryParam("firstname", "Susan");
		//Get response with booking ids
		Response response = RestAssured.given().spec(spec).get("/booking");
		response.print();
		
		//Verify the response
		Assert.assertEquals(response.getStatusCode(),200,"Status code should be 200. but its different");
		
		//Verify atleast one booking id is in response
		List<Integer> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(),"List of booking Ids is empty, but it shouldn't be");
		
	}
}
