package com.vmwaretalentboost.ics.controllers;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageControllerTest {
    private static String BaseUrl = "http://localhost:8080";
    private static String imagesUrl = BaseUrl + "/images";



//    @BeforeEach
//    public void setUp(){
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 8080;
//    }

    @Test
    void getAllImages() {

    }

    @Test
    void getImageAndTagsById() {

    }

    @Test
    void addImageByUrl() {
        
    }

    @Test
    void deleteImageByUrl() {
    }
}
