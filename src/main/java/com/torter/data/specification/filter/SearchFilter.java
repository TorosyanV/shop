package com.torter.data.specification.filter;


import java.util.List;

/**
 * Created by Zhirayr.Gumruyan on 12/6/2016.
 */
public class SearchFilter {

    private Boolean customCleared;
    private Integer minYear;
    private Double maxPrice;

    private Long makeId;
    private Long modelId;
    private Long regionId;
    private Long cityId;


    private Integer maxYear;
    private Double minPrice;


    private Boolean metallic;
    private List<Long> colorIds;
    private List<Long> bodyTypeIds;

    private Long capacityId;

    private Boolean damaged;
    private Boolean immediately;

    private Long countryId;

    private Long fuelTypeId;
    private Long gearTypeId;

    private Boolean withImage;


    public Boolean getCustomCleared() {
        return customCleared;
    }

    public void setCustomCleared(Boolean customCleared) {
        this.customCleared = customCleared;
    }

    public Integer getMinYear() {
        return minYear;
    }

    public void setMinYear(Integer minYear) {
        this.minYear = minYear;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Long getMakeId() {
        return makeId;
    }

    public void setMakeId(Long makeId) {
        this.makeId = makeId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
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

    public Integer getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public List<Long> getBodyTypeIds() {
        return bodyTypeIds;
    }

    public void setBodyTypeIds(List<Long> bodyTypeIds) {
        this.bodyTypeIds = bodyTypeIds;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Boolean getMetallic() {
        return metallic;
    }

    public void setMetallic(Boolean metallic) {
        this.metallic = metallic;
    }

    public List<Long> getColorIds() {
        return colorIds;
    }

    public void setColorIds(List<Long> colorIds) {
        this.colorIds = colorIds;
    }

    public Long getCapacityId() {
        return capacityId;
    }

    public void setCapacityId(Long capacityId) {
        this.capacityId = capacityId;
    }

    public Boolean getDamaged() {
        return damaged;
    }

    public void setDamaged(Boolean damaged) {
        this.damaged = damaged;
    }

    public Boolean getImmediately() {
        return immediately;
    }

    public void setImmediately(Boolean immediately) {
        this.immediately = immediately;
    }

    public Long getCountryId() {
        return countryId;
    }


    public Long getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(Long fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }



    public Long getGearTypeId() {
        return gearTypeId;
    }

    public void setGearTypeId(Long gearTypeId) {
        this.gearTypeId = gearTypeId;
    }

    public Boolean getWithImage() {
        return withImage;
    }

    public void setWithImage(Boolean withImage) {
        this.withImage = withImage;
    }
}
