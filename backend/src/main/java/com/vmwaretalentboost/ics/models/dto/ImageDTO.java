package com.vmwaretalentboost.ics.models.dto;

import com.vmwaretalentboost.ics.models.Image;
import com.vmwaretalentboost.ics.models.ImageTags;

import java.util.List;

public class ImageDTO {
    private Image image;
    private List<ImageTagsDTO> imageTagsList;

    public ImageDTO() {
    }

    public ImageDTO(Image image, List<ImageTagsDTO> imageTagsList) {
        this.image = image;
        this.imageTagsList = imageTagsList;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<ImageTagsDTO> getImageTagsList() {
        return imageTagsList;
    }

    public void setImageTagsList(List<ImageTagsDTO> imageTagsList) {
        this.imageTagsList = imageTagsList;
    }
}
