package tests;

import baseUrl.SpecJsonPlaceHolder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import testDatas.JsonPlaceData;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C19_Put_TestDataClassKullanimi extends SpecJsonPlaceHolder {

     /*
        https://jsonplaceholder.typicode.com/posts/65 url'ine
        bir GET request yolladigimizda donen response’in
            status kodunun 200 olduğunu
            Connection Header değerinin keep-alive olduğunu
            ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

        Response body :
        {
             "userId": 7,
             "id": 65,
             "title": "consequatur id enim sunt et et",
             "body": "voluptatibus ex esse\nsint explicabo est aliquid cumque adipisci fuga repellat labore\nmolestiae corrupti ex saepe at asperiores et perferendis\nnatus id esse incidunt pariatur"
        }
     */

    /*
    https://jsonplaceholder.typicode.com/posts/71
    {
    "userId": 8,
    "id": 71,
    "title": "et iusto veniam et illum aut fuga",
    "body": "occaecati a doloribus\niste saepe consectetur placeat eum voluptate dolorem et\nqui quo quia voluptas\nrerum ut id enim velit est perferendis"
}
     */
    @Test
    public void test(){

        //1- Endpoint hazırlama( Request Body verilirse o da burada hazırlanır)
        specJsonPlaceHolder.pathParams("pp1","posts","pp2","71");
        //2- Eğer verilmişse Expected Body hazırlanır( verilmemişse bu adımda bir işlem yapılmaz)
        JSONObject expBody= JsonPlaceData.JsonDataOlustur(8,71,"et iusto veniam et illum aut fuga",
                "occaecati a doloribus\niste saepe consectetur placeat eum voluptate dolorem et\nqui quo quia voluptas\nrerum ut id enim velit est perferendis");

        //3- Donen Response kayıt altına alınır
        Response response=given().when().spec(specJsonPlaceHolder).get("/{pp1}/{pp2}");
        //4- Donen Response ile beklenen body assert edilir.
        JsonPath resJp=response.jsonPath();
        assertEquals(JsonPlaceData.basariliSC,response.getStatusCode());
        assertEquals(JsonPlaceData.contentType,response.getContentType());
        assertEquals(JsonPlaceData.header,response.getHeader("Connection"));
        assertEquals(expBody.get("userId"),resJp.get("userId"));
        assertEquals(expBody.get("id"),resJp.get("id"));
        assertEquals(expBody.get("title"),resJp.get("title"));
        assertEquals(expBody.get("body"),resJp.get("body"));
    }



}
