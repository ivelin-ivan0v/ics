package com.vmwaretalentboost.ics.controllers;

import com.vmwaretalentboost.ics.functional.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class ImageAddTest extends BaseTest {
    private static String realImageUrl = "https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*";
    private static String validNotImageUrl = "https://www.genesis.com/uk/documents/legal/owner_manuals/2023/owner-manual_g70_en_23.pdf";
    private static String invalidUrl = "https://www.domatikrastavici.com";

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
}
