package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C14_Put_SoftAssertIleExpectedDataTesti {
    /*
    https://dummy.restapiexample.com/api/v1/update/21 url’ine
    asagidaki body’ye sahip bir PUT request gonderdigimizde donen response’un
    asagidaki gibi oldugunu test edin.

    Request Body                        Response Body(Expected)
 {                                  {
    "status": "success",                 "status": "success",
    "data": {                            "data": {
        "name": “Ahmet",                            "name": “Ahmet",
        "salary": "1230",                           "salary": "1230",
        "age": "44",                                "age": "44",
        "id": 40                                    "id": 40
    }                                             }
}                                       "message": "Successfully! Record has been updated."
}


 */

    @Test
    public void test() {

        //1- Endpoint hazırlama( Request Body verilirse o da burada hazırlanır)
        String url = "https://dummy.restapiexample.com/api/v1/update/21 ";
        JSONObject innerData = new JSONObject();
        innerData.put("name", "Ahmet");
        innerData.put("salary", "1230");
        innerData.put("age", "44");
        innerData.put("id", 40);
        JSONObject reqBody = new JSONObject();
        reqBody.put("status", "success");
        reqBody.put("data", innerData);
        //2- Eğer verilmişse Expected Body hazırlanır( verilmemişse bu adımda bir işlem yapılmaz)
        JSONObject expBody = new JSONObject();
        expBody.put("status", "success");
        expBody.put("data", innerData);
        expBody.put("message", "Successfully! Record has been updated.");
        //3- Donen Response kayıt altına alınır

        Response response = given().contentType(ContentType.JSON).when().body(reqBody.toString()).put(url);

        //4- Donen Response ile beklenen body assert edilir.
        JsonPath resJP = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resJP.get("status"), expBody.get("status"));
        softAssert.assertEquals(resJP.get("data.name"), expBody.getJSONObject("data").get("name"));
        softAssert.assertEquals(resJP.get("data.salary"), expBody.getJSONObject("data").get("salary"));
        softAssert.assertEquals(resJP.get("data.age"), expBody.getJSONObject("data").get("age"));
        softAssert.assertEquals(resJP.get("data.id"), expBody.getJSONObject("data").get("id"));
        softAssert.assertEquals(resJP.get("message"), expBody.get("message"));
    }
}
