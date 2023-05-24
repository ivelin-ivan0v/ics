package com.vmwaretalentboost.ics.controllers;

import com.vmwaretalentboost.ics.models.Tag;
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

    @GetMapping("{tagName}/exists")
    public ResponseEntity<Boolean> tagExists(@PathVariable String tagName) {
        //return tagServiceImpl.tagExists(tagName);
        return ResponseEntity.ok(tagServiceImpl.tagExists(tagName));
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagServiceImpl.getAllTags();
    }


}
