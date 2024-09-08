package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class SpecHerOkuApp {

    protected RequestSpecification specHerOkuApp;

    @BeforeTest
    protected void setup(){
        specHerOkuApp=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/").build();


    }
}
