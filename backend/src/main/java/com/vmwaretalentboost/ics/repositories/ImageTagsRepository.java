package com.vmwaretalentboost.ics.repositories;

import com.vmwaretalentboost.ics.models.Image;
import com.vmwaretalentboost.ics.models.ImageTags;
import com.vmwaretalentboost.ics.models.ImageTagsCompositeKey;
import com.vmwaretalentboost.ics.models.dto.ImageTagsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageTagsRepository extends JpaRepository<ImageTags, ImageTagsCompositeKey> {
//    @Query("SELECT COUNT(u) FROM ImageTags u WHERE u.tagName=?1")
//    Long countByTag(String tagName);

    void deleteAllById(Image image);

    @Query("SELECT new com.vmwaretalentboost.ics.models.dto.ImageTagsDTO(it.tagName.tagName, it.confidence) FROM ImageTags it WHERE it.id =?1")
    List<ImageTagsDTO> getTagsAndConfidenceByImage(Image image);

    List<ImageTags> findAllByTagName(String tagName);

}
