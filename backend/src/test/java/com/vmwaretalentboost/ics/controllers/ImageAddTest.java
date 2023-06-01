package com.vmwaretalentboost.ics.controllers;

import com.vmwaretalentboost.ics.functional.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.springframework.test.context.event.annotation.AfterTestClass;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class ImageAddTest extends BaseTest {

    @Test
    void addImageByUrl() {
        given()
                .param("url", realImageUrl)
                .when()
                .post(IMAGES_URL)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("image.url", is(realImageUrl))
                .body("image.width", is(greaterThan(0)))
                .body("image.height", is(greaterThan(0)))
                .body("image.id", is(notNullValue()))
                .body("image.analysisService", is(notNullValue()))
                .body("imageTagsList", not(empty()));
    }

    @Test
    void addImageByInvalidUrl() {
        given()
                .param("url", invalidUrl)
                .when()
                .post(IMAGES_URL)
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void addImageByNotImageUrl() {
        given()
                .param("url", validNotImageUrl)
                .when()
                .post(IMAGES_URL)
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @AfterTestClass
    void TearDown() {

    }
}
