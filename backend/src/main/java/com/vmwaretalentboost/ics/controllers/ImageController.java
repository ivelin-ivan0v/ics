package com.vmwaretalentboost.ics.controllers;

import com.vmwaretalentboost.ics.models.Image;
import com.vmwaretalentboost.ics.models.dto.ImageDTO;
import com.vmwaretalentboost.ics.models.dto.ImageTagsDTO;
import com.vmwaretalentboost.ics.services.impl.ImageServiceImpl;
import com.vmwaretalentboost.ics.services.impl.ImageTagsServiceImpl;
import com.vmwaretalentboost.ics.utils.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageServiceImpl imageServiceImpl;
    private final ImageTagsServiceImpl imageTagsServiceImpl;

    @Autowired
    public ImageController(ImageServiceImpl imageServiceImpl, ImageTagsServiceImpl imageTagsServiceImpl) {
        this.imageServiceImpl = imageServiceImpl;
        this.imageTagsServiceImpl = imageTagsServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> getAllImages(@RequestParam(required = false, defaultValue = "") List<String> tags) {
        //returns all images if no tags are given as parameters
        if (tags.isEmpty()) {
            return ResponseEntity.ok(imageServiceImpl.getAllImages());
        }

        List<ImageDTO> returnList = new ArrayList<>();

        //iterates every image to get its tags
        for (ImageDTO imageDTO : imageServiceImpl.getAllImages()) {
            List<String> onlyTagNamesList = new ArrayList<>();
            for (ImageTagsDTO imageTagsDTO : imageDTO.getImageTagsList()) {
                onlyTagNamesList.add(imageTagsDTO.getTagName());
            }
            boolean flag = true;
            //checks if every tag from parameters exists in the image_tags
            for (String tag : tags) {
                if (!onlyTagNamesList.contains(tag)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                returnList.add(imageDTO);
            }
        }
        //returns only images that have all tags from the parameters
        return ResponseEntity.ok(returnList);
    }

    @GetMapping("{id}")
    public ResponseEntity<ImageDTO> getImageAndTagsById(@PathVariable Long id) {
        if (imageServiceImpl.imageExists(id)) {
            return ResponseEntity.ok(imageServiceImpl.getImageAndTagsById(id));
        } else return ResponseEntity.notFound().build();
    }

    /*
     * Returns Image with tags because id is required in frontend
     *  so everything can be done in one request instead of 2
     * */
    @PostMapping
    public ResponseEntity<ImageDTO> addImageByUrl(@RequestParam String url, @RequestParam(defaultValue = "false") boolean noCache) throws IOException {
        if (!URLValidator.urlExists(url) || !URLValidator.isImageURL(url)) {
            return ResponseEntity.badRequest().build();
        }

        if (!imageServiceImpl.imageExists(url) || noCache) {
            // Remove existing image tags if noCache is true
            if (noCache) {
                imageServiceImpl.deleteImageByUrl(url);
            }
            imageServiceImpl.addImage(url);
        }
        Image image = imageServiceImpl.getImageByUrl(url);

        //return ResponseEntity.ok(imageTagsServiceImpl.getImageTagsByImage(image));
        return ResponseEntity.ok(imageServiceImpl.getImageAndTagsById(image.getId()));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteImageByUrl(@RequestParam String url) {
        imageServiceImpl.deleteImageByUrl(url);
        return ResponseEntity.noContent().build();
    }


}
