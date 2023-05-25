package com.vmwaretalentboost.ics.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
//@IdClass(ImageTagsCompositeKey.class)
@Table(name = "image_tags")
public class ImageTags {
    @JsonIgnore
    @EmbeddedId
    ImageTagsCompositeKey imageTagsCompositeKey;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "image_id")
    private Image id;

    @ManyToOne
    @MapsId("tagName")
    @JoinColumn(name = "tag_name")
    private Tag tagName;

    @Column(name = "confidence")
    private float confidence;

    public ImageTags() {
    }

    public ImageTags(ImageTagsCompositeKey imageTagsCompositeKey, Image id, Tag tagName, float confidence) {
        this.imageTagsCompositeKey = imageTagsCompositeKey;
        this.id = id;
        this.tagName = tagName;
        this.confidence = confidence;
    }

    public ImageTagsCompositeKey getImageTagsCompositeKey() {
        return imageTagsCompositeKey;
    }

    public void setImageTagsCompositeKey(ImageTagsCompositeKey imageTagsCompositeKey) {
        this.imageTagsCompositeKey = imageTagsCompositeKey;
    }

    public Image getId() {
        return id;
    }

    public void setId(Image id) {
        this.id = id;
    }

    public Tag getTagName() {
        return tagName;
    }

    public void setTagName(Tag tagName) {
        this.tagName = tagName;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageTags imageTags = (ImageTags) o;
        return Float.compare(imageTags.confidence, confidence) == 0 && Objects.equals(imageTagsCompositeKey, imageTags.imageTagsCompositeKey) && Objects.equals(id, imageTags.id) && Objects.equals(tagName, imageTags.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageTagsCompositeKey, id, tagName, confidence);
    }

    @Override
    public String toString() {
        return "ImageTags{" +
                "imageTagsCompositeKey=" + imageTagsCompositeKey +
                ", id=" + id +
                ", tagName=" + tagName +
                ", confidence=" + confidence +
                '}';
    }
}
