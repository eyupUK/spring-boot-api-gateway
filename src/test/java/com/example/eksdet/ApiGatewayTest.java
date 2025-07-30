package com.example.eksdet;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiGatewayTest {

    @Test
    public void gatewayAll() {
        given().when()
                .get("/gateway/apps")
                .then().statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void gatewayByName_found() {
        given().when()
                .get("/gateway/apps/appOne")
                .then().statusCode(200)
                .body("appName", equalTo("appOne"));
    }

    @Test
    public void gatewayByName_notFound() {
        given().when()
                .get("/gateway/apps/nonexistent")
                .then().statusCode(404).body("error", equalTo("No apps found for the given name"));
    }

    @Test
    public void gatewayByOwner_found() {
        given().when()
                .get("/gateway/apps?owner=ownerOne")
                .then().statusCode(200)
                .body("appData.appOwner", everyItem(equalTo("ownerOne")));
    }

    @Test
    public void gatewayByOwner_notFound() {
        given().when()
                .get("/gateway/apps?owner=unknownTeam")
                .then().statusCode(404).body("error", equalTo("No apps found for the given owner"));
    }
}