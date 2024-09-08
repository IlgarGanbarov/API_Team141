package tests;

import baseUrl.SpecHerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.PojoHerOkuAppExp;
import pojos.PojoHerokuAppBookingdates;
import pojos.PojoHerokuAppReq;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class C26_Post_Pojo extends SpecHerOkuApp {
    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un asagidaki gibi oldugunu test edin.
    	                Request body
    	           {
    	                "firstname" : "Ahmet",
    	                "lastname" : "Bulut",
    	                "totalprice" : 500,
    	                "depositpaid" : false,
    	                "bookingdates" : {
    	                         "checkin" : "2021-06-01",
    	                         "checkout" : "2021-06-10"
    	                                  },
    	                "additionalneeds" : "wi-fi"
    	            }


    	            	Response Body // expected data
    	            {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                        ,
                        "additionalneeds":"wi-fi"
                    }
     */
    @Test
    public void test() {
        specHerOkuApp.pathParam("pp1", "booking");
        PojoHerokuAppBookingdates bookingdatesPOJO = new PojoHerokuAppBookingdates("2021-06-01", "2021-06-10");
        PojoHerokuAppReq reqBody = new PojoHerokuAppReq("Ahmet", "Bulut", 500, false, "wi-fi", bookingdatesPOJO);

        PojoHerokuAppReq bookingPOJO = new PojoHerokuAppReq("Ahmet", "Bulut", 500, false, "wi-fi", bookingdatesPOJO);
        PojoHerOkuAppExp expBody = new PojoHerOkuAppExp(24, bookingPOJO);

        Response response = given().spec(specHerOkuApp).contentType(ContentType.JSON).when().body(reqBody).post("{pp1}");

        PojoHerOkuAppExp resPJO = response.as(PojoHerOkuAppExp.class);
        //assertEquals(resPJO.getBookingid(),expBody.getBookingid());
        assertEquals(resPJO.getBooking().getFirstname(), expBody.getBooking().getFirstname());
        assertEquals(resPJO.getBooking().getLastname(), expBody.getBooking().getLastname());
        assertEquals(resPJO.getBooking().getTotalprice(), expBody.getBooking().getTotalprice());
        assertEquals(resPJO.getBooking().getAdditionalneeds(), expBody.getBooking().getAdditionalneeds());
        assertEquals(resPJO.getBooking().isDepositpaid(), expBody.getBooking().isDepositpaid());
        assertEquals(resPJO.getBooking().getBookingdates().getCheckin(), expBody.getBooking().getBookingdates().getCheckin());
        assertEquals(resPJO.getBooking().getBookingdates().getCheckout(), expBody.getBooking().getBookingdates().getCheckout());


    }
}
