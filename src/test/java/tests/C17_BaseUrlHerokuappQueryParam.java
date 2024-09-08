package tests;

import baseUrl.SpecHerOkuApp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C17_BaseUrlHerokuappQueryParam extends SpecHerOkuApp {

    /*
    https://restful-booker.herokuapp.com/booking endpointine
    gerekli Query parametrelerini yazarak “firstname” degeri “Jim” olan
    rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
    donen response’un status code’unun 200 oldugunu ve
    “Jim” ismine sahip en az bir booking oldugunu test edin
     */

    @Test
    public void test01() {
        //1- Endpoint hazırlama( Request Body verilirse o da burada hazırlanır)
        specHerOkuApp.pathParam("pp1", "booking").queryParam("firstname", "Eric");

        //2- Eğer verilmişse Expected Body hazırlanır( verilmemişse bu adımda bir işlem yapılmaz)
        //3- Donen Response kayıt altına alınır
        Response response = given().when().spec(specHerOkuApp).get("{pp1}");
        //4- Donen Response ile beklenen body assert edilir.
        response.then().assertThat().statusCode(200).body("size()", Matchers.greaterThan(1));


    }

    @Test
    public void test02() {
        /*
        https://restful-booker.herokuapp.com/booking endpointine
        gerekli Query parametrelerini yazarak “firstname” degeri “Josh” ve “lastname” degeri “Allen” olan
        rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
        donen response’un status code’unun 200 oldugunu ve “Josh Allen”
        ismine sahip en az bir booking oldugunu test edin
    */


        //1- Endpoint hazırlama( Request Body verilirse o da burada hazırlanır)
        specHerOkuApp.pathParam("pp1", "booking").queryParams("firstname", "Josh", "lastname", "Allen");
        //2- Eğer verilmişse Expected Body hazırlanır( verilmemişse bu adımda bir işlem yapılmaz)

        //3- Donen Response kayıt altına alınır
        Response response = given().when().spec(specHerOkuApp).get("{pp1}");
        //4- Donen Response ile beklenen body assert edilir.
        response.then().assertThat().statusCode(200).body("size()", Matchers.greaterThan(1));

    }


}
