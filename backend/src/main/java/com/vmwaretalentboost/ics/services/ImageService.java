package com.vmwaretalentboost.ics.services;


import com.vmwaretalentboost.ics.models.Image;
import com.vmwaretalentboost.ics.models.dto.ImageDTO;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image addImage(String url) throws IOException;

    void deleteImageByUrl(String url);

    void deleteImageById(Image image);

    Object getImageById(Long id);

    public ImageDTO getImageAndTagsById(Long id);

    public List<ImageDTO> getAllImages();

    Image getImageByUrl(String url);

    boolean imageExists(String url);

    boolean imageExists(Long id);
}
