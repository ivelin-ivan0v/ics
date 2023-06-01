package com.vmwaretalentboost.ics.models.dto;

public class TagDTO {
    private String tagName;
    private Long count;

    public TagDTO() {
    }

    public TagDTO(String tagName, Long count) {
        this.tagName = tagName;
        this.count = count;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
