package com.vmwaretalentboost.ics.services;

import com.vmwaretalentboost.ics.models.Image;

import java.util.List;

public interface ImageTagsService {

    void addImageTags(Image image);

    void deleteImageTags(Image image);

    List<Object[]> getImageTagsByImage(Image image);
}
