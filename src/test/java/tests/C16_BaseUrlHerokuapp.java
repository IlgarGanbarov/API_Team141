package tests;

import baseUrl.SpecHerOkuApp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

;

public class C16_BaseUrlHerokuapp extends SpecHerOkuApp {

    /*
       Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin
   1-  https://restful-booker.herokuapp.com/booking endpointine bir GET request gonderdigimizde
   donen response’un status code’unun 200 oldugunu ve Response’ta 12 booking oldugunu test edin
   2- https://restful-booker.herokuapp.com/booking endpointine asagidaki body’ye sahip bir POST request gonderdigimizde
    donen response’un status code’unun 200 oldugunu ve “firstname” degerinin “Ahmet” oldugunu test edin
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

 */
    @Test
    public void test01() {
        //1- Endpoint hazırlama( Request Body verilirse o da burada hazırlanır)
        specHerOkuApp.pathParam("pp1", "booking");

        //2- Eğer verilmişse Expected Body hazırlanır( verilmemişse bu adımda bir işlem yapılmaz)
        //3- Donen Response kayıt altına alınır
        Response response = given().when().spec(specHerOkuApp).get("/{pp1}");
        //4- Donen Response ile beklenen body assert edilir.
        response.then().assertThat().statusCode(200).body("bookingid", Matchers.hasSize(12));


    }

    @Test
    public void test02() {
        //1- Endpoint hazırlama( Request Body verilirse o da burada hazırlanır)
        specHerOkuApp.pathParam("pp1", "booking");
        JSONObject innerData = new JSONObject();
        innerData.put("checkin", "2021-06-01");
        innerData.put("checkout", "2021-06-10");
        JSONObject reqData = new JSONObject();
        reqData.put("firstname", "Ahmet");
        reqData.put("lastname", "Bulut");
        reqData.put("totalprice", 500);
        reqData.put("depositpaid", false);
        reqData.put("bookingdates", innerData);
        reqData.put("additionalneeds", "wi-fi");

        //2- Eğer verilmişse Expected Body hazırlanır( verilmemişse bu adımda bir işlem yapılmaz)

        //3- Donen Response kayıt altına alınır
        Response response = given().contentType(ContentType.JSON).when().spec(specHerOkuApp).body(reqData.toString()).post("/{pp1}");

        //4- Donen Response ile beklenen body assert edilir.

        response.then().assertThat().statusCode(200).body("booking.firstname", Matchers.equalTo("Ahmet"));


    }


}
