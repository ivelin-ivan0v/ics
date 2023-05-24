package com.vmwaretalentboost.ics.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", unique = true)
    private Long id;

    @Column(name = "url", unique = true)
    private String url;

    @Column(name = "analysis_time")
    private LocalDateTime analysisTime;
    @Column(name = "analysis_service")
    private String analysisService;
    @Column(name = "width")
    private int width;
    @Column(name = "height")
    private int height;

    public Image() {
    }

    public Image(Long id, String url, LocalDateTime analysisTime, String analysisService, int width, int height) {
        this.id = id;
        this.url = url;
        this.analysisTime = analysisTime;
        this.analysisService = analysisService;
        this.width = width;
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getAnalysisTime() {
        return analysisTime;
    }

    public void setAnalysisTime(LocalDateTime analysisTime) {
        this.analysisTime = analysisTime;
    }

    public String getAnalysisService() {
        return analysisService;
    }

    public void setAnalysisService(String analysisService) {
        this.analysisService = analysisService;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return width == image.width && height == image.height && Objects.equals(id, image.id) && Objects.equals(url, image.url) && Objects.equals(analysisTime, image.analysisTime) && Objects.equals(analysisService, image.analysisService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, analysisTime, analysisService, width, height);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", analysisTime=" + analysisTime +
                ", analysisService='" + analysisService + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
