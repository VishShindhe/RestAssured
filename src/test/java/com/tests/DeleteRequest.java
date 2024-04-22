package com.tests;


import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequest {

    @Test
    public void deleteTest() {
        given()
                .pathParam("id", 3)
                .log()
                .all()
                .delete("http://localhost:3000/employees/{id}");

    }
}
