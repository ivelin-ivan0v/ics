package com.vmwaretalentboost.ics.services.impl;

import com.vmwaretalentboost.ics.models.Image;
import com.vmwaretalentboost.ics.models.dto.ImageDTO;
import com.vmwaretalentboost.ics.repositories.ImageRepository;
import com.vmwaretalentboost.ics.services.ImageService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageTagsServiceImpl imageTagsService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ImageTagsServiceImpl imageTagsService1) {
        this.imageRepository = imageRepository;
        this.imageTagsService = imageTagsService1;
    }

    @Override
    public Image addImage(String url) throws IOException {
        Image image = new Image();
        image.setAnalysisTime(LocalDateTime.now());
        image.setUrl(url);

        URL tempUrl = new URL(url);
        BufferedImage tempImage = ImageIO.read(tempUrl);
        image.setWidth(tempImage.getWidth());
        image.setHeight(tempImage.getHeight());

        imageRepository.save(image);
        imageTagsService.addImageTags(imageRepository.findByUrl(url));
        image.setAnalysisService("Clarifai");
        return image;
    }

    @Override
    public void deleteImageByUrl(String url) {
        Image image = imageRepository.findByUrl(url);
        imageTagsService.deleteImageTags(image);
        //imageTagsService.deleteImageTags(imageRepository.findByUrl(url).getId());
        //imageTagsService.deleteImageTags(getImageByUrl(url).getId());
        imageRepository.deleteByUrl(url);
    }

    @Override
    public void deleteImageById(Image image) {
        imageTagsService.deleteImageTags(image);
        imageRepository.deleteById(image.getId());
    }

    @Override
    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public ImageDTO getImageAndTagsById(Long id) {
        ImageDTO imageDTO = new ImageDTO();
        try {
            Image image = imageRepository.findById(id).get();
            imageDTO.setImage(image);
            imageDTO.setImageTagsList(imageTagsService.getImageTagsByImage(image));
            return imageDTO;
        } catch (NoSuchElementException ex) {
            return imageDTO;
        }

    }

    @Override
    public List<ImageDTO> getAllImages() {
        List<Image> images = imageRepository.findAll();
        List<ImageDTO> returnList = new ArrayList<>();
        for (Image image : images) {
            returnList.add(getImageAndTagsById(image.getId()));
        }
        return returnList;
    }

    @Override
    public Image getImageByUrl(String url) {
        return imageRepository.findByUrl(url);
    }

    @Override
    public boolean imageExists(String url) {
        return imageRepository.existsByUrl(url);
    }

    @Override
    public boolean imageExists(Long id) {
        return imageRepository.existsById(id);
    }


}
