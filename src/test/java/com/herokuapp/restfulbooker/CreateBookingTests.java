package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateBookingTests extends BaseTest {

	 @Test
	public void createBookingTest() {
		// Get response with booking ids
		Response response = createBooking();
		response.print();

		// Verify the response
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200. but its different");

		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		String actualFirstName = response.jsonPath().getString("firstname");
		softAssert.assertEquals(actualFirstName, "John", "firstname in response is not expected");

		String actualLastName = response.jsonPath().getString("lastname");
		softAssert.assertEquals(actualLastName, "Smith", "lastname in response is not expected");

		int price = response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(price, 1599, "totalprice in response is not expected");

		boolean depositpaid = response.jsonPath().getBoolean("depositpaid");
		softAssert.assertTrue(depositpaid, "depositpaid should be true, but it's not");

		String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2018-01-01", "checkin in response is not expected");

		String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2019-01-01", "checkout in response is not expected");

		String actualAdditionalneeds = response.jsonPath().getString("additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Breakfast", "additionalneeds in response is not expected");

		softAssert.assertAll();
	}

	//@Test
	public void createBookingWithPOJOTest() {
		/// Create body using POJOs
		Bookingdates bookingdates = new Bookingdates("2020-03-25", "2020-03-27");
		Booking booking = new Booking("Olga", "Shyshkin", 200, false, bookingdates, "Baby crib");

		// Get response
		Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(booking).post("/booking");
		response.print();
		
		// Verifications
		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		String actualFirstName = response.jsonPath().getString("booking.firstname");
		softAssert.assertEquals(actualFirstName, "Olga", "firstname in response is not expected");

		String actualLastName = response.jsonPath().getString("booking.lastname");
		softAssert.assertEquals(actualLastName, "Shyshkin", "lastname in response is not expected");

		int price = response.jsonPath().getInt("booking.totalprice");
		softAssert.assertEquals(price, 200, "totalprice in response is not expected");

		boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
		softAssert.assertFalse(depositpaid, "depositpaid should be false, but it's not");

		String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2020-03-25", "checkin in response is not expected");

		String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2020-03-27", "checkout in response is not expected");

		String actualAdditionalneeds = response.jsonPath().getString("booking.additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Baby crib", "additionalneeds in response is not expected");

		softAssert.assertAll();
	}
	
	//@Test
	public void createBookingWithPOJOTest2() {
		// Create body using POJOs
		Bookingdates bookingdates = new Bookingdates("2020-03-25", "2020-03-27");
		Booking booking = new Booking("Olga", "Shyshkin", 200, false, bookingdates, "Baby crib");

		// Get response
		Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(booking)
				.post("/booking");
		response.print();
		Bookingid bookingid = response.as(Bookingid.class);

		// Verifications
		// Verify response 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

		System.out.println("Request booking : " + booking.toString());
		System.out.println("Response booking: " + bookingid.getBooking().toString());
		
		// Verify All fields
		Assert.assertEquals(bookingid.getBooking().toString(), booking.toString());
	}
}
