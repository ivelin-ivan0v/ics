package com.vmwaretalentboost.ics.models.dto;

public class ImageTagsDTO {
    private String tagName;

    private float confidence;

    public ImageTagsDTO() {
    }

    public ImageTagsDTO(String tagName, float confidence) {
        this.tagName = tagName;
        this.confidence = confidence;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }
}
