package com.tests;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import static  io.restassured.RestAssured.*;
import io.restassured.response.Response;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

public class GetRequest {

    @Test
    public void getTest(){
        Response response = given().get("http://localhost:3000/employees");
        response.prettyPrint();
        System.out.println("Status code " + response.getStatusCode());
        System.out.println("Response Time " + response.getTime());
        System.out.println("Content-Type is: "+ response.getHeader("Content-Type"));

        //Get all headers from the response
        Headers headers = response.headers();
        System.out.println("======> Headers for the response are: <====== ");
        for(Header header: headers){
            System.out.println(header.getName() +": "+header.getValue());
        }
    }
}
