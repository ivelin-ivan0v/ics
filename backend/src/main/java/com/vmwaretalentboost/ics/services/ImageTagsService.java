package com.vmwaretalentboost.ics.services;

import com.vmwaretalentboost.ics.models.Image;
import com.vmwaretalentboost.ics.models.ImageTags;
import com.vmwaretalentboost.ics.models.dto.ImageTagsDTO;

import java.util.List;

public interface ImageTagsService {

    void addImageTags(Image image);

    void deleteImageTags(Image image);

    List<ImageTagsDTO> getImageTagsByImage(Image image);

    public List<ImageTags> getImagesHavingTag(String tagName);
}
