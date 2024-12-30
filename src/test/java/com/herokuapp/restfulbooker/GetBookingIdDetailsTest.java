package com.herokuapp.restfulbooker;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetBookingIdDetailsTest extends BaseTest {

	@Test(enabled = false)
	public void getBookingIDTest() {
		
		spec.pathParam("ID", 163);

		// Get response with booking ids
		Response response = RestAssured.given().spec(spec).get("/booking/{ID}");
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
		softAssert.assertEquals(price, 111, "totalprice in response is not expected");

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
	
	@Test
	public void getBookingIDTestXML() {
		
		spec.pathParam("ID", 662);

		// Get response with booking ids
		Header xml = new Header("accept","application/xml");
		spec.header(xml);
		Response response = RestAssured.given().spec(spec).get("/booking/{ID}");
		response.print();

		// Verify the response
		Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200. but its different");

		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		String actualFirstName = response.xmlPath().getString("booking.firstname");
		softAssert.assertEquals(actualFirstName, "Dmitry", "firstname in response is not expected");

		String actualLastName = response.xmlPath().getString("booking.lastname");
		softAssert.assertEquals(actualLastName, "Shyshkin", "lastname in response is not expected");

		int price = response.xmlPath().getInt("booking.totalprice");
		softAssert.assertEquals(price, 150, "totalprice in response is not expected");

		boolean depositpaid = response.xmlPath().getBoolean("booking.depositpaid");
		softAssert.assertFalse(depositpaid, "depositpaid should be false, but it's not");

		String actualCheckin = response.xmlPath().getString("booking.bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2020-03-25", "checkin in response is not expected");

		String actualCheckout = response.xmlPath().getString("booking.bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2020-03-27", "checkout in response is not expected");

		String actualAdditionalneeds = response.xmlPath().getString("booking.additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Baby crib", "additionalneeds in response is not expected");

		softAssert.assertAll();

	}

}
