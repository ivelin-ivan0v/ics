package com.vmwaretalentboost.ics.services.impl;

import com.clarifai.grpc.api.Concept;
import com.vmwaretalentboost.ics.models.Image;
import com.vmwaretalentboost.ics.models.ImageTags;
import com.vmwaretalentboost.ics.models.ImageTagsCompositeKey;
import com.vmwaretalentboost.ics.models.dto.ImageTagsDTO;
import com.vmwaretalentboost.ics.repositories.ImageTagsRepository;
import com.vmwaretalentboost.ics.services.ImageTagsService;
import com.vmwaretalentboost.ics.utils.ClarifaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImageTagsServiceImpl implements ImageTagsService {
    private final ImageTagsRepository imageTagsRepository;
    private final TagServiceImpl tagService;

    @Autowired
    public ImageTagsServiceImpl(ImageTagsRepository imageTagsRepository, TagServiceImpl tagService) {
        this.imageTagsRepository = imageTagsRepository;
        this.tagService = tagService;
    }

    @Override
    public void addImageTags(Image image) {
        image.setAnalysisService("Clarifai");
        List<Concept> conceptList = ClarifaiService.classifyImage(image.getUrl());
        ImageTags imageTags;
        ImageTagsCompositeKey imageTagsCompositeKey;
        for (Concept concept : conceptList) {
            if (!tagService.tagExists(concept.getName())) {
                tagService.addTag(concept.getName());
            }
            imageTagsCompositeKey = new ImageTagsCompositeKey(image.getId(), concept.getName());
            imageTags = new ImageTags(imageTagsCompositeKey, image, tagService.getTag(concept.getName()), concept.getValue());
            imageTagsRepository.save(imageTags);
        }
    }

    @Override
    public void deleteImageTags(Image image) {
        imageTagsRepository.deleteAllById(image);
        tagService.deleteTagsWithoutImages();
    }

    @Override
    public List<ImageTagsDTO> getImageTagsByImage(Image image) {
        //return imageTagsRepository.findAllById(image);
        return imageTagsRepository.getTagsAndConfidenceByImage(image);
    }

    @Override
    public List<ImageTags> getImagesHavingTag(String tagName) {
        return imageTagsRepository.findAllByTagName(tagName);
    }

}
