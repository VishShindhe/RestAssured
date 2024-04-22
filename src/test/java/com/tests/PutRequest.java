package com.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test
public class PutRequest {

    public void putTest(){
        JSONObject obj = new JSONObject();
        obj.put("first_name","Tim");
        obj.put("last_name", "cook");
        obj.put("email", "cook@test.com");


        Response response = given()
                .pathParam("id", 3)
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(obj.toMap())
                .put("http://localhost:3000/employees/{id}");

        response.prettyPrint();

    }
}
