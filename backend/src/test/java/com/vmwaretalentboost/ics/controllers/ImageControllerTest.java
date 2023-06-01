package com.vmwaretalentboost.ics.controllers;

import com.vmwaretalentboost.ics.functional.BaseTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class ImageControllerTest extends BaseTest {

    @Test
    void getAllImages() {

    }

    @Test
    void getImageAndTagsById() {

    }

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

    @Test
    void deleteImageByUrl() {
        given()
                .param("url", realImageUrl)
                .when()
                .delete(IMAGES_URL)
                .then().assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
