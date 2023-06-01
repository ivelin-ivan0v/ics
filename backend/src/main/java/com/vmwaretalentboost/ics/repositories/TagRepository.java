package com.vmwaretalentboost.ics.repositories;

import com.vmwaretalentboost.ics.models.Image;
import com.vmwaretalentboost.ics.models.Tag;
import com.vmwaretalentboost.ics.models.dto.ImageTagsDTO;
import com.vmwaretalentboost.ics.models.dto.TagDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    Tag findByTagName(String tagName);

    @Query("SELECT new com.vmwaretalentboost.ics.models.dto.TagDTO(t.tagName, COUNT(it.tagName)) FROM Tag t JOIN ImageTags it WHERE t.tagName = it.tagName GROUP BY t.tagName")
    List<TagDTO> getTagsAndCount();

    @Modifying
    @Query("DELETE FROM Tag t WHERE NOT EXISTS (SELECT 1 FROM ImageTags it WHERE it.tagName = t.tagName)")
    void deleteTagsNotInImageTags();
}
