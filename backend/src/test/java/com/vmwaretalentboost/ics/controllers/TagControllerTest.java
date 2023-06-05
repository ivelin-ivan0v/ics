package com.vmwaretalentboost.ics.controllers;

import com.vmwaretalentboost.ics.functional.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


class TagControllerTest extends BaseTest {
    @Test
    void getAllTags() {
        given()
                .when()
                .get(TAG_URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(tagsJsonSchemaPath)));
    }
}
