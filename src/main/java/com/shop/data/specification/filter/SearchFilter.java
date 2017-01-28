package com.shop.data.specification.filter;


import java.util.List;

/**
 * Created by Zhirayr.Gumruyan on 12/6/2016.
 */
public class SearchFilter {

    private Double maxPrice;
    private Double minPrice;
    private Long regionId;
    private Long cityId;
    private Long countryId;
    private Boolean withImage;

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }


    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }


    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCountryId() {
        return countryId;
    }


    public Boolean getWithImage() {
        return withImage;
    }

    public void setWithImage(Boolean withImage) {
        this.withImage = withImage;
    }
}
