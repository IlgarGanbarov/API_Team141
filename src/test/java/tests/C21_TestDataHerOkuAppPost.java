package tests;

import baseUrl.SpecHerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import testDatas.HerOkuAppData;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C21_TestDataHerOkuAppPost extends SpecHerOkuApp {

    /*
           https://restful-booker.herokuapp.com/booking url'ine
           asagidaki body'ye sahip bir POST request gonderdigimizde
           donen response'un bookingid haric asagidaki gibi oldugunu test edin.
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
          Expected response body
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
                                       },
                       "additionalneeds":"wi-fi"
                               }
                     }
    */
    @Test
    public void test() {
        //1- Endpoint hazırlama( Request Body verilirse o da burada hazırlanır)
        specHerOkuApp.pathParam("pp1", "booking");
        JSONObject reqBody = HerOkuAppData.reqDataOlustur();
        //2- Eğer verilmişse Expected Body hazırlanır( verilmemişse bu adımda bir işlem yapılmaz)
        JSONObject expBody = HerOkuAppData.expDataOlustur();
        //3- Donen Response kayıt altına alınır
        Response response = given().contentType(ContentType.JSON).when()
                .spec(specHerOkuApp).body(reqBody.toString()).post("{pp1}");
        //4- Donen Response ile beklenen body assert edilir.

        JsonPath resJP = response.jsonPath();
        assertEquals(expBody.getJSONObject("booking").get("firstname"), resJP.get("booking.firstname"));
        assertEquals(expBody.getJSONObject("booking").get("lastname"), resJP.get("booking.lastname"));
        assertEquals(expBody.getJSONObject("booking").get("totalprice"), resJP.get("booking.totalprice"));
        assertEquals(expBody.getJSONObject("booking").get("depositpaid"), resJP.get("booking.depositpaid"));
        assertEquals(expBody.getJSONObject("booking").get("additionalneeds"), resJP.get("booking.additionalneeds"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"), resJP.get("booking.bookingdates.checkin"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"), resJP.get("booking.bookingdates.checkout"));


    }


}
