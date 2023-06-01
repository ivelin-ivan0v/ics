package com.vmwaretalentboost.ics.functional;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    private static String BASE_URL = "http://localhost";
    private static int PORT = 8080;

    protected static String IMAGES_URL = "/images";
    protected static String realImageUrl = "https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg?crop=1.00xw:0.753xh;0,0.153xh&resize=1200:*";
    protected static String validNotImageUrl = "https://www.genesis.com/uk/documents/legal/owner_manuals/2023/owner-manual_g70_en_23.pdf";
    protected static String invalidUrl = "https://www.domatikrastavici.com";


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = PORT;
    }
}
