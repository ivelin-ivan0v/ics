package com.vmwaretalentboost.ics.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ImageTagsCompositeKey implements Serializable {
    @Column(name = "image_id")
    private Long id;
    @Column(name = "tag_name")
    private String tagName;

    public ImageTagsCompositeKey() {
    }

    public ImageTagsCompositeKey(Long imageId, String tagName) {
        this.id = imageId;
        this.tagName = tagName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long imageId) {
        this.id = imageId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageTagsCompositeKey that = (ImageTagsCompositeKey) o;
        return Objects.equals(id, that.id) && Objects.equals(tagName, that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName);
    }

    @Override
    public String toString() {
        return "ImageTagsCompositeKey{" +
                "imageId=" + id +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
