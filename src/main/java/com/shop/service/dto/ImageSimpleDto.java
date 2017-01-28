package com.shop.service.dto;

/**
 * Created by Vazgen on 24-Dec-16.
 */
public class ImageSimpleDto implements ImageUrl {

    private Long id;
    private String imageName;

    public Long getId() {
        return id;
    }

    public String getUrl() {

        return "https://vuvustorage.blob.core.windows.net/images/" + imageName + ".jpg";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
