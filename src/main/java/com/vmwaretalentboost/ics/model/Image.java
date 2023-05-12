package com.vmwaretalentboost.ics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Image {
    @Id
    private String url;
    private LocalDateTime uploadTime;
    private LocalDateTime analysisTime;
    private String analysisService;
    private int width;
    private int height;
}
