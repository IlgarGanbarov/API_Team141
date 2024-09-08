package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {

    /*
       https://restful-booker.herokuapp.com/booking url'ine
       asagidaki body'ye sahip bir POST request gonderdigimizde
       donen response'un bookingid haric asagidaki gibi oldugunu test edin.

       Request body
       {
       "firstname" : "Hasan",
       "lastname" : "Yagmur",
       "totalprice" : 500,
       "depositpaid" : false,
       "bookingdates" : {
           "checkin" : "2021-06-01",
           "checkout" : "2021-06-10"
           },
       "additionalneeds" : "wi-fi"
       }

       Response Body
       {
       "bookingid":24,
       "booking":{
           "firstname":"Hasan",
           "lastname":"Yagmur",
           "totalprice":500,
           "depositpaid":false,
           "bookingdates":{
               "checkin":"2021-06-01",
               "checkout":"2021-06-10"
               },
       "additionalneeds":"wi-fi"
       }
       }
        */
    @Test
    public void test(){
        String url="https://restful-booker.herokuapp.com/booking";

        /*
        "checkin" : "2021-06-01",
           "checkout" : "2021-06-10"

           "firstname" : "Hasan",
       "lastname" : "Yagmur",
       "totalprice" : 500,
       "depositpaid" : false,
       "bookingdates" :
         */
        JSONObject innerData=new JSONObject();
        innerData.put("checkin" , "2021-06-01");
        innerData.put("checkout", "2021-06-10");
        JSONObject reqBody=new JSONObject();
        reqBody.put("firstname" , "Hasan");
        reqBody.put("lastname" , "Yagmur");
        reqBody.put("totalprice" ,500);
        reqBody.put("depositpaid" ,false);
        reqBody.put("bookingdates",innerData);
        reqBody.put("additionalneeds","wi-fi");
        JSONObject exBody=new JSONObject();
        exBody.put("firstname","Hasan");
        exBody.put("booking",reqBody);
        Response response=given().contentType(ContentType.JSON).when().body(reqBody.toString()).post(url);

        JsonPath resJS=response.jsonPath();
        assertEquals(exBody.getJSONObject("booking").get("firstname"),resJS.get("booking.firstname"));
        assertEquals(exBody.getJSONObject("booking").get("lastname"),resJS.get("booking.lastname"));
        assertEquals(exBody.getJSONObject("booking").get("totalprice"),resJS.get("booking.totalprice"));
        assertEquals(exBody.getJSONObject("booking").get("depositpaid"),resJS.get("booking.depositpaid"));
        assertEquals(exBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),resJS.get("booking.bookingdates.checkin"));
        assertEquals(exBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),resJS.get("booking.bookingdates.checkout"));
        assertEquals(exBody.getJSONObject("booking").get("additionalneeds"),resJS.get("booking.additionalneeds"));










    }





















}
