package com.vmwaretalentboost.ics.controllers;

import com.vmwaretalentboost.ics.functional.BaseTest;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ImageControllerTest extends BaseTest {
    @Test
    void getAllImagesWithoutParam() {
        given()
                .when()
                .get(IMAGES_URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(imagesJsonSchemaPath)));
    }

    @Test
    void getAllImagesWithOneParam() {
        List<String> tags = List.of("car");

        given()
                .param("tags", tags)
                .when()
                .get(IMAGES_URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(imagesJsonSchemaPath)));
    }

    @Test
    void getAllImagesWithMultiParams() {
        List<String> tags = Arrays.asList("cat", "sleep");

        given()
                .param("tags", tags)
                .when()
                .get(IMAGES_URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(imagesJsonSchemaPath)));
    }

    /*
     * image with the custom id will always exist
     * as it cannot be removed from UI
     * */
    @Test
    void getImageAndTagsById() {
        int idOfExistingImage = 32;

        given()
                .pathParam("id", idOfExistingImage)
                .when()
                .get(IMAGES_URL_BY_ID)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(imageJsonSchemaPath)))
                .body("image.url", is(realImageUrlExistingInDB))
                .body("image.width", is(greaterThan(0)))
                .body("image.height", is(greaterThan(0)))
                .body("image.id", is(idOfExistingImage))
                .body("image.analysisService", is(notNullValue()))
                .body("imageTagsList", not(empty()));
    }

    /*
     * image ID is always larger than other id's in database
     * so image with id-1 will never exist
     * */
    @Test
    void getImageAndTagsByInvalidId() {
        int idOfExistingImage = 1;

        given()
                .pathParam("id", idOfExistingImage)
                .when()
                .get(IMAGES_URL_BY_ID)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void addExistingImageByUrl() {
        given()
                .param("url", realImageUrlExistingInDB)
                .when()
                .post(IMAGES_URL)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(imageJsonSchemaPath)))
                .body("image.url", is(realImageUrlExistingInDB))
                .body("image.width", is(greaterThan(0)))
                .body("image.height", is(greaterThan(0)))
                .body("image.id", is(notNullValue()))
                .body("image.analysisService", is(notNullValue()))
                .body("imageTagsList", not(empty()));
    }

    @Test
    @Order(1)
    void deleteImageByUrl() {
        given()
                .param("url", realImageUrlForDeleteThenAdd)
                .when()
                .delete(IMAGES_URL)
                .then().assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Order(2)
    void addNotExistingImageByUrl() {
        given()
                .param("url", realImageUrlForDeleteThenAdd)
                .when()
                .post(IMAGES_URL)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(imageJsonSchemaPath)))
                .body("image.url", is(realImageUrlForDeleteThenAdd))
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
}
