package tests;

import baseUrl.SpecHerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testDatas.HerOkuAppData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C24_Post_Deserialization extends SpecHerOkuApp {
    /*
    https://restful-booker.herokuapp.com/booking url'ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response'un asagidaki gibi oldugunu test edin.
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
                            "checkout":"2021-06-10",
                        "additionalneeds":"wi-fi"
                    }
     */
    @Test
    public void test(){

        specHerOkuApp.pathParam("pp1", "booking");
        Map<String, Object> reqBodyMAP = HerOkuAppData.reqDataOlsuturMAP();

        Map<String, Object> expBodyMAP = HerOkuAppData.expMapDataOlustur();

        Response response=given().spec(specHerOkuApp).contentType(ContentType.JSON)
                .when().body(reqBodyMAP).post("/{pp1}");


        Map<String, Object> resMAP=response.as(HashMap.class);

        assertEquals(((Map)expBodyMAP.get("booking")).get("firstname"),((Map)resMAP.get("booking")).get("firstname"));
        assertEquals(((Map)expBodyMAP.get("booking")).get("lastname"),((Map)resMAP.get("booking")).get("lastname"));
        assertEquals(((Map)expBodyMAP.get("booking")).get("totalprice"),((Map)resMAP.get("booking")).get("totalprice"));
        assertEquals(((Map)expBodyMAP.get("booking")).get("depositpaid"),((Map)resMAP.get("booking")).get("depositpaid"));
        assertEquals(((Map)expBodyMAP.get("booking")).get("additionalneeds"),((Map)resMAP.get("booking")).get("additionalneeds"));

        assertEquals(((Map)((Map)expBodyMAP.get("booking")).get("bookingdates")).get("checkin"),
                ((Map)((Map)resMAP.get("booking")).get("bookingdates")).get("checkin"));

        assertEquals(((Map)((Map)expBodyMAP.get("booking")).get("bookingdates")).get("checkout"),
                ((Map)((Map)resMAP.get("booking")).get("bookingdates")).get("checkout"));

    }
}
