package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APITest {

	@Test
	public void apitestscenarios() {

		// Create json body
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

		Response response = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(body.toString())
				.post("https://restful-booker.herokuapp.com/booking");
		
		Response response2 = RestAssured
				.given()
				.get("https://restful-booker.herokuapp.com/booking/985");
		
		
		response.print();
		response2.print();
		
		
		// Verify the response
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200. but its different");

		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		String actualFirstName = response.jsonPath().getString("booking.firstname");
		softAssert.assertEquals(actualFirstName, "Ben", "firstname in response is not expected");

		String actualLastName = response.jsonPath().getString("booking.lastname");
		softAssert.assertEquals(actualLastName, "Tenison", "lastname in response is not expected");

		int price = response.jsonPath().getInt("booking.totalprice");
		softAssert.assertEquals(price, 1599, "totalprice in response is not expected");

		boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
		softAssert.assertTrue(depositpaid, "depositpaid should be true, but it's not");

		String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2013-02-21", "checkin in response is not expected");

		String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2020-03-27", "checkout in response is not expected");

		String actualAdditionalneeds = response.jsonPath().getString("booking.additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Breakfast", "additionalneeds in response is not expected");

		softAssert.assertAll();
	}

}
