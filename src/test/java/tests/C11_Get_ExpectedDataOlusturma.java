package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class C11_Get_ExpectedDataOlusturma {

    /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request yolladigimizda donen response body’sinin asagida verilen ile ayni oldugunu test ediniz
  Response body :
{
    "userId": 3,
    "id": 22,
    "title": "dolor sint quo a velit explicabo quia nam",
    "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
            um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
}
     */
    @Test
    public void test(){
        String url="https://jsonplaceholder.typicode.com/posts/22";
        // expected body hazirlama

        JSONObject exBody=new JSONObject();

        exBody.put("userId", 3);

        exBody.put("id", 22);


        exBody.put( "title", "dolor sint quo a velit explicabo quia nam");

        exBody.put("body", "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear"+
        "um mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        Response response=given().when().get(url);

        JsonPath resJP=response.jsonPath();

        assertEquals(exBody.get("userId"),resJP.get("userId"));

        assertEquals(exBody.get("id"),resJP.get("id"));

        assertEquals(exBody.get("title"),resJP.get("title"));

        assertEquals(exBody.get("body"),resJP.get("body"));












    }
}
