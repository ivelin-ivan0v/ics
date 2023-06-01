package com.vmwaretalentboost.ics.services;

import com.vmwaretalentboost.ics.models.Tag;
import com.vmwaretalentboost.ics.models.dto.TagDTO;

import java.util.List;

public interface TagService {
     Tag addTag(String tagName);

      List<TagDTO> getTagsAndCount();

     void deleteTagsWithoutImages();

     boolean tagExists(String tagName);

     Tag getTag(String tagName);

     List<Tag> getAllTags();
}
