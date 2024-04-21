package com.tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.given;

public class PostRequest {

  /*  @Test
    //Using String as the post body
    //Pros: Simple to use and can be used to quickly check the functionality
    //Cons: Cannot be used for complex and dynamic json

    public void postTest(){

        String reqBody = "{\n" +
                "        \"id\": 7,\n" +
                "        \"first_name\": \"Sheldon\",\n" +
                "        \"last_name\": \"Copper\",\n" +
                "        \"email\": \"sheldon@test.com\"\n" +
                "    }";
        Response response = given()
                .header("Content-Type","application/json")
                //.header("Content-Type", ContentType.JSON)    <<< Can be written this way also
                .body(reqBody)
                .log()
                .all()
                .post("http://localhost:3000/employees");
        response.prettyPrint();
    }

    @Test
    public void postTest2(){

        //Using external file. Mainly used for static json request
        //Pros: Increases readability as the request body is stored in external file
        //Cons: Cannot the print the request read from the file and cannot be used for dynamic json requests

        Response response = given()
                .header("Content-Type",ContentType.JSON)
                .log()
                .all()
                .body(new File(System.getProperty("user.dir") + "/test.json"))
                .post("http://localhost:3000/employees");
        response.prettyPrint();

    }

    @Test
    public void postTest3() throws IOException {

        //Read request from external file and convert it to string
        //Pros: Logs the request body to the console and can change few parameters in the request
        //Cons: Cannot the print the request read from the file and cannot be used for dynamic json requests

        byte[] bytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/test.json"));
        String reqBody = new String(bytes);

        String replace = reqBody.replace("9","10");

        Response response = given()
                .header("Content-Type",ContentType.JSON)
                .log()
                .all()
                .body(replace)
                .post("http://localhost:3000/employees");

        response.prettyPrint();

    }

    @Test
    public void postTest4() throws IOException {

        //Read request from external file and convert it to string and update using java faker
        //Pros: Logs the request body to the console and can change few parameters in the request
        //Cons: Cannot the print the request read from the file and cannot be used for dynamic json requests

        byte[] bytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/test.json"));
        String reqBody = new String(bytes);

        String replace = reqBody.replace("empno",String.valueOf(new Faker().number().numberBetween(100,1000)))
                .replace("fname", new Faker().name().firstName())
                .replace("lname", new Faker().name().lastName())
                .replace("emailAddress", new Faker().internet().emailAddress());

        Response response = given()
                .header("Content-Type",ContentType.JSON)
                .log()
                .all()
                .body(replace)
                .post("http://localhost:3000/employees");

        response.prettyPrint();

    }*/

    @Test
    public void postTest5() throws IOException {

        //Using map and list from java
        // { }  --> Use Map interface
        // [ ] --> List
        // CONS: verbose and not suitable for very big json files and generic type needs to be mentioned (Object)
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

        Map<String, Object> obj = new LinkedHashMap<>();  // Use LinkedHashMap to maintain order of json properties, HashMap wont maintain the order
        obj.put("id",String.valueOf(new Faker().number().numberBetween(100,1000)));
        obj.put("first_name", "Vishwa");
        obj.put("last_name", "Shindhe");
        obj.put("email", "vs@test.com");
        obj.put("email", "vs@gmail.com"); //email will updated to vs@gmsil.com by overwriting prev value

        /*List<String> listOfJobs = new ArrayList<>();
        listOfJobs.add("tester");
        listOfJobs.add("leader");
        obj.put("jobs", listOfJobs);*/

        obj.put("jobs", Arrays.asList("tester","trainer")); //Easier way to add list

        Map<String,Object> food = new HashMap<>();
        food.put("breakfast", "idly");

        /*List<String> lunchFood = new ArrayList<>();
        lunchFood.add("Chapati");
        lunchFood.add("Paneer");
        food.put("dinner", "curdRice");
        food.put("lunch", lunchFood); */

        food.put("lunch", Arrays.asList("Chapati","Paneer"));

        obj.put("FavFoods",food);

        Response response = given()
                .header("Content-Type",ContentType.JSON)
                .log()
                .all()
                .body(obj)
                .post("http://localhost:3000/employees");

        response.prettyPrint();

    }

    @Test
    public void postTest6() throws IOException {

        //Using external json library
        // It has some collections that can solve problems we has while using map and list
        // { }  --> JsonObject
        // [ ] --> JsonArray


        JSONObject obj = new JSONObject();
        obj.put("id",String.valueOf(new Faker().number().numberBetween(100,1000)));
        obj.put("first_name", "Vishwa");
        obj.put("last_name", "Shindhe");
        obj.put("email", "vs@test.com");
        obj.accumulate("email", "vs@gmail.com");
        obj.putOpt("newEmail", null);  // the object newEmail is added only if value is NOT NULL
        //obj.putOnce("fname", "Rock"); // This will throw exception as fname already exists.

        JSONArray listOfJobs = new JSONArray();
        listOfJobs.put("tester");
        listOfJobs.put("leader");
        obj.put("jobs", listOfJobs);

        JSONObject food = new JSONObject();
        food.put("breakfast", "idly");

        JSONArray lunchFood = new JSONArray();
        lunchFood.put("Chapati");
        lunchFood.put("Paneer");
        food.put("dinner", "curdRice");
        food.put("lunch", lunchFood);

        obj.put("FavFoods",food);

        Response response = given()
                .header("Content-Type",ContentType.JSON)
                .log()
                .all()
                .body(obj.toMap())
                .post("http://localhost:3000/employees");

        response.prettyPrint();

    }


}
