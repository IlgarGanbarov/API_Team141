package tests;

import baseUrl.SpecJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import testDatas.JsonPlaceData;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C20_TestDataJsonPlacePut extends SpecJsonPlaceHolder {
    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine
    asagidaki body'e sahip bir PUT request yolladigimizda
    donen response'in
        status kodunun 200,
        content type'inin "application/json; charset=utf-8",
        Connection header degerinin "keep-alive"
        ve response body'sinin asagida verilen ile ayni oldugunu test ediniz
     Request Body
        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":7,
        "id":70
        }
    Response body :
        {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
        }
 */
    @Test
    public void test01() {
        //1- Endpoint hazırlama( Request Body verilirse o da burada hazırlanır)
        specJsonPlaceHolder.pathParams("pp1", "posts", "pp2", "70");
        JSONObject reqData = JsonPlaceData.JsonDataOlustur(10, 70, "Ahmet", "Merhaba");


        //2- Eğer verilmişse Expected Body hazırlanır( verilmemişse bu adımda bir işlem yapılmaz)
        JSONObject expData = JsonPlaceData.JsonDataOlustur(10, 70, "Ahmet", "Merhaba");

        //3- Donen Response kayıt altına alınır
        Response response = given().contentType(ContentType.JSON).when().spec(specJsonPlaceHolder).body(reqData.toString()).put("/{pp1}/{pp2}");

        //4- Donen Response ile beklenen body assert edilir.
        JsonPath resJP = response.jsonPath();

        // System.out.println(reqData);
        //System.out.println(expData);
        assertEquals(JsonPlaceData.basariliSC, response.getStatusCode());

        assertEquals(JsonPlaceData.contentType, response.getContentType());

        assertEquals(JsonPlaceData.header, response.getHeader("Connection"));

        assertEquals(expData.get("userId"), resJP.getInt("userId"));

        assertEquals(expData.get("id"), resJP.getInt("id"));

        assertEquals(expData.get("title"), resJP.get("title"));

        assertEquals(expData.get("body"), resJP.get("body"));

        response.prettyPrint();


    }

}
