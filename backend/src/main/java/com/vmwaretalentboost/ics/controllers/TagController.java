package com.vmwaretalentboost.ics.controllers;

import com.vmwaretalentboost.ics.models.Tag;
import com.vmwaretalentboost.ics.models.dto.TagDTO;
import com.vmwaretalentboost.ics.services.impl.TagServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagServiceImpl tagServiceImpl;

    @Autowired
    public TagController(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagServiceImpl.getAllTags();
    }

    /*
    * /tags returns only tagName because it is implemented in the service to delete not existing tags
    * and count variable is not required but i still added it as an additional endpoint tags/count
    * */
    @GetMapping("/count")
    public List<TagDTO> getAllTagCount() {
        return tagServiceImpl.getTagsAndCount();
    }
}
