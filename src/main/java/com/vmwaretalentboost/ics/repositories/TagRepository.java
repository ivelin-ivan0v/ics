package com.vmwaretalentboost.ics.repositories;

import com.vmwaretalentboost.ics.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    Tag findByTagName(String tagName);

    @Modifying
    @Query("DELETE FROM Tag t WHERE NOT EXISTS (SELECT 1 FROM ImageTags it WHERE it.tagName = t.tagName)")
    void deleteTagsNotInImageTags();
}
