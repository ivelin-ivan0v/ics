package com.vmwaretalentboost.ics.services;


import com.vmwaretalentboost.ics.models.Image;

import java.io.IOException;

public interface ImageService {
    Image addImage(String url) throws IOException;

    void deleteImageByUrl(String url);

    void deleteImageById(Image image);

    Object getImageById(Long id);

    Image getImageByUrl(String url);

    boolean imageExists(String url);

    boolean imageExists(Long id);
}
