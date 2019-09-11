package utils;

import java.util.*;
import java.io.IOException;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;


public class RequestMethods {
    public static Response response;

    public RequestMethods(){

    }

    public static Properties loadConfigProperties() throws IOException {
        Properties props = new Properties();
        props.load(RequestMethods.class.getClassLoader().getResourceAsStream("config.properties"));

        return props;
    }

    public static Properties loadMessageProperties() throws IOException {
        Properties props = new Properties();
        props.load(RequestMethods.class.getClassLoader().getResourceAsStream("message.properties"));

        return props;
    }


    public static Response getResponse(String endPoint) throws IOException {

        RestAssured.baseURI = loadConfigProperties().getProperty("api.uri");
        RequestSpecification requestSpecification = given();
        response = requestSpecification.header("Authorization", "Bearer "+loadConfigProperties().get("bearer_token")).get(RestAssured.baseURI+"/"+endPoint);
        return response;
    }

    public static int getStatusCode(String status) throws IOException {
       return Integer.parseInt(loadConfigProperties().getProperty(status));
    }

    public List<Header> getHeaders(){
        return response.getHeaders().asList();
    }

    public Map setGenericHeaders(){
        Map<String,Object> headerMap = new HashMap<String,Object>();
        headerMap.put("Header1", "Value1");
        headerMap.put("Header2", "Value2");

        return headerMap;
    }


    public static Response postJsonPayload(Object payload, String endPoint) throws IOException {
        return
                given()
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .post(endPoint)
                        .then()
                        .statusCode(getStatusCode("StatusCode_OK"))
                        .extract()
                        .response();
    }

    public  Response getJsonResponse(String payload, String endPoint) throws IOException {
        return
                given()
                        .get(endPoint)
                        .then()
                        .statusCode(getStatusCode("StatusCode_OK"))
                        .extract()
                        .response();
    }


    //Submit Post Request with Form Parameters
    public void submitForm() throws IOException {
        RestAssured.baseURI = "https://www.example.com";
        given().urlEncodingEnabled(true)
                .param("username", "user@site.com")
                .param("password", "Pas54321")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post("/login")
                .then().statusCode(getStatusCode("StatusCode_OK"));
    }

}
