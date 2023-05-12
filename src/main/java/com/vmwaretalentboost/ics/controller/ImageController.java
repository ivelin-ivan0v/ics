package com.vmwaretalentboost.ics.controller;

import com.vmwaretalentboost.ics.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;
    //@Autowired
    //private TagRepositoryImpl tagRepository;

//    @GetMapping("/images")
//    public boolean imageTags(@RequestParam("url") String image_url) throws SQLException {
//        Map<String, Float> map = imageService.classifyImage(image_url);
//
//        boolean flag = true;
//
//        for (Map.Entry<String, Float> entry : map.entrySet()) {
//            String tag = entry.getKey();
//            float confidence = entry.getValue();
//            //tagRepository.updateTag(tag);
//            if (!flag) {
//                return flag;
//            }
//
//        }


        //return imageService.classifyImage(image_url);
        //return flag;
   //}


//    @GetMapping("/images")
//    public String getImageTags(@RequestParam String url) {
//        return url;
//    }
}
