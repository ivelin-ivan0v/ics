package com.vmwaretalentboost.ics.controllers;

import com.vmwaretalentboost.ics.models.dto.TagDTO;
import com.vmwaretalentboost.ics.services.impl.TagServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tags")
public class TagController {
    private final TagServiceImpl tagServiceImpl;

    @Autowired
    public TagController(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }

    @GetMapping
    public List<TagDTO> getAllTags() {
        return tagServiceImpl.getTagsAndCount();
    }
}
