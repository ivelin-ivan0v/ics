package com.vmwaretalentboost.ics.functional;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    private static String BASE_URL = "http://localhost";
    private static int PORT = 8080;

    protected static String IMAGES_URL = "/images";
    protected static String TAG_URL = "/tags";
    protected static String IMAGES_URL_BY_ID = IMAGES_URL + "/{id}";

    protected static String realImageUrlExistingInDB = "https://hips.hearstapps.com/hmg-prod/images/cute-cat-photos-1593441022.jpg";
    protected static String realImageUrlForDeleteThenAdd = "https://w.wallhaven.cc/full/47/wallhaven-47o5j9.jpg";
    protected static String validNotImageUrl = "https://www.genesis.com/uk/documents/legal/owner_manuals/2023/owner-manual_g70_en_23.pdf";
    protected static String invalidUrl = "https://www.domatikrastavici.com";

    protected static String imageJsonSchemaPath = "src/JSONSchemas/imageJsonSchema.json";
    protected static String imagesJsonSchemaPath = "src/JSONSchemas/imagesJsonSchema.json";
    protected static String tagsJsonSchemaPath = "src/JSONSchemas/tagsJsonSchema.json";


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = PORT;
    }
}
