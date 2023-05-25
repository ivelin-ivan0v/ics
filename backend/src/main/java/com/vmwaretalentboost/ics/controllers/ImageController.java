package com.vmwaretalentboost.ics.controllers;

import com.vmwaretalentboost.ics.models.Image;
import com.vmwaretalentboost.ics.services.impl.ImageServiceImpl;
import com.vmwaretalentboost.ics.services.impl.ImageTagsServiceImpl;
import com.vmwaretalentboost.ics.utils.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("{id}")
    public void getImageAndTagsById(@PathVariable Long id) {

    }

    @PostMapping
    public ResponseEntity<List<Object[]>> addImageByUrl(@RequestParam String url, @RequestParam(defaultValue = "false") boolean noCache) throws IOException {
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

        return ResponseEntity.ok(imageTagsServiceImpl.getImageTagsByImage(image));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteImageByUrl(@RequestParam String url) {
        imageServiceImpl.deleteImageByUrl(url);
        return ResponseEntity.noContent().build();
    }


}
