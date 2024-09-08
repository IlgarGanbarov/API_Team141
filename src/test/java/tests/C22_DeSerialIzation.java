package tests;

import baseUrl.SpecJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import testDatas.JsonPlaceData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C22_DeSerialIzation extends SpecJsonPlaceHolder {
    /*
https://jsonplaceholder.typicode.com/posts/70 url'ine
asagidaki body’e sahip bir PUT request yolladigimizda
donen response’in response body’sinin asagida verilen ile ayni oldugunu test ediniz

    Request Body

    {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
    }

 Response Body:

    {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
    }
 */
    @Test
    public void test() {
        //1- Endpoint hazırlama( Request Body verilirse o da burada hazırlanır)
        specJsonPlaceHolder.pathParams("pp1", "posts", "pp2", "70");
        Map<String, Object> reqMapBody = JsonPlaceData.reqMapBodyOlustur();
        //2- Eğer verilmişse Expected Body hazırlanır( verilmemişse bu adımda bir işlem yapılmaz)
        Map<String, Object> expMapBody = JsonPlaceData.reqMapBodyOlustur();
        //3- Donen Response kayıt altına alınır
        Response response = given().contentType(ContentType.JSON).when()
                .spec(specJsonPlaceHolder)
                .body(reqMapBody).put("{pp1}/{pp2}");
        //4- Donen Response ile beklenen body assert edilir.
        Map<String, Object>resMap=response.as(HashMap.class);

        assertEquals(expMapBody.get("title"),resMap.get("title"));
        assertEquals(expMapBody.get("body"),resMap.get("body"));
        assertEquals(expMapBody.get("id"),resMap.get("id"));
        assertEquals(expMapBody.get("userId"),resMap.get("userId"));



    }


}
