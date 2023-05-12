package com.vmwaretalentboost.ics.model;


import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    private String tag_name;

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag_name='" + tag_name + '\'' +
                '}';
    }
}
