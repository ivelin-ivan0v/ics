package com.vmwaretalentboost.ics.services.impl;

import com.vmwaretalentboost.ics.models.Tag;
import com.vmwaretalentboost.ics.repositories.TagRepository;
import com.vmwaretalentboost.ics.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag addTag(String tagName) {
        Tag tag = new Tag(tagName);
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTagsWithoutImages() {
        tagRepository.deleteTagsNotInImageTags();
    }

    @Override
    public boolean tagExists(String tagName) {
        return tagRepository.existsById(tagName);
    }

    @Override
    public Tag getTag(String tagName) {
        return tagRepository.findByTagName(tagName);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
