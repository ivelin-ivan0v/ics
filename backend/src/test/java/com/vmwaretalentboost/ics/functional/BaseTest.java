package com.vmwaretalentboost.ics.functional;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    private static String BASE_URL = "http://localhost";
    private static int PORT = 8080;

    protected static String IMAGES_URL = "/images";


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = PORT;
    }
}
