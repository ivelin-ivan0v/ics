package com.vmwaretalentboost.ics.utils;

import com.clarifai.channel.ClarifaiChannel;
import com.clarifai.credentials.ClarifaiCallCredentials;
import com.clarifai.grpc.api.*;
import com.clarifai.grpc.api.status.StatusCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClarifaiService {
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // In this section, we set the user authentication, user and app ID, model details, and the URL
    // of the image we want as an input. Change these strings to run your own example.
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    // Your PAT (Personal Access Token) can be found in the portal under Authentication
    static final String PAT = "8d8d2a26996746ff9e4ac30d4c13331e";
    // Specify the correct user_id/app_id pairings
    // Since you're making inferences outside your app's scope
    static final String USER_ID = "ivelin-ivanov";
    static final String APP_ID = "image-classification-service";
    // Change these to whatever model and image URL you want to use
    static final String MODEL_ID = "general-image-recognition";
    static final String MODEL_VERSION_ID = "aa7f35c01e0642fda5cf400f543e7c40";
    static final String IMAGE_URL = "https://samples.clarifai.com/metro-north.jpg";

    static final String ANALYSIS_SERVICE = "Clarifai";

    ///////////////////////////////////////////////////////////////////////////////////
    // YOU DO NOT NEED TO CHANGE ANYTHING BELOW THIS LINE TO RUN THIS EXAMPLE
    ///////////////////////////////////////////////////////////////////////////////////

    public static List<Concept> classifyImage(String image_url) {
        Map<String, Float> tags = new HashMap<>();

        V2Grpc.V2BlockingStub stub = V2Grpc.newBlockingStub(ClarifaiChannel.INSTANCE.getGrpcChannel())
                .withCallCredentials(new ClarifaiCallCredentials(PAT));

        MultiOutputResponse postModelOutputsResponse = stub.postModelOutputs(
                PostModelOutputsRequest.newBuilder()
                        .setUserAppId(UserAppIDSet.newBuilder().setUserId(USER_ID).setAppId(APP_ID))
                        .setModelId(MODEL_ID)
                        .setVersionId(MODEL_VERSION_ID)  // This is optional. Defaults to the latest model version.
                        .addInputs(
                                Input.newBuilder().setData(
                                        Data.newBuilder().setImage(
                                                Image.newBuilder().setUrl(image_url)
                                        )
                                )
                        )
                        .build()
        );

        if (postModelOutputsResponse.getStatus().getCode() != StatusCode.SUCCESS) {
            throw new RuntimeException("Post model outputs failed, status: " + postModelOutputsResponse.getStatus());
        }

        // Since we have one input, one output will exist here.
        Output output = postModelOutputsResponse.getOutputs(0);

//        for (Concept concept : output.getData().getConceptsList()) {
//            System.out.printf("%s %.2f%n", concept.getName(), concept.getValue());
//        }

        return output.getData().getConceptsList();
    }
}
