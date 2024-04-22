package com.tests;

import com.github.javafaker.Faker;
import com.pojo.Employee;
import com.pojo.FavFood;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static  io.restassured.RestAssured.*;

public class PostRequestUsingPojo {

    //POJO - Plain Old Java Object
    //{} --> Create class file
    //[] -->  List<Type>

    // Sample Post body
    /*
    {
            "id": 1212,
            "first_name": "Vishwa",
            "last_name": "Shindhe",
            "email": "vs@test.com",
            "jobs": ["tester", "leader"],
            "favFoods": {
                "breakfast": "idly",
                "lunch": ["Chapati", "Paneer"],
                "dinner":"curdRice"
            }
        }
     */

    @Test
    public void pojoTest() {

        List<String> lunch = new ArrayList<>();
        lunch.add("Chapathi");
        lunch.add("Paneer");
        FavFood favFood = new FavFood("Dosa",lunch,"Salad");
        Employee employee = new Employee((new Faker().number().numberBetween(100,1000)), "abcd", "xyz","ax@test.com",favFood, Arrays.asList("tester","leader"));

        Response response = given()
                .header("Content-Type", ContentType.JSON)
                .log()
                .all()
                .body(employee)
                .post("http://localhost:3000/employees");
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),201);
        System.out.println(response.jsonPath().getString("email"));
        System.out.println(response.jsonPath().getList("jobs"));

        Employee deserialisedEmp = response.as(Employee.class); //json --> class
        System.out.println(deserialisedEmp.getEmail());
        System.out.println(deserialisedEmp.getJobs());

        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

    }
}

