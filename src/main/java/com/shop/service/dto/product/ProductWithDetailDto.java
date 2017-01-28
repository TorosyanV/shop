package com.shop.service.dto.product;


import com.shop.service.dto.ImageSimpleDto;
import com.shop.service.dto.location.RegionSimpleDto;
import com.shop.service.dto.CompositionSimpleDto;
import com.shop.service.dto.ImageUrl;
import com.shop.service.dto.location.CitySimpleDto;

import java.util.List;

/**
 * Created by Vazgen on 08/25/2016.
 */
public class ProductWithDetailDto {


    private  long carId;
    private List<ImageSimpleDto> images;
    private ImageUrl mainImage;
    private ProductDetailDto detail;
    private RegionSimpleDto region;
    private CitySimpleDto city;

    private List<CompositionSimpleDto> equipments;

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }








    public ImageUrl getMainImage() {
        return mainImage;
    }

    public void setMainImage(ImageUrl mainImage) {
        this.mainImage = mainImage;
    }


    public CitySimpleDto getCity() {
        return city;
    }

    public void setCity(CitySimpleDto city) {
        this.city = city;
    }

    public ProductDetailDto getDetail() {
        return detail;
    }

    public void setDetail(ProductDetailDto detail) {
        this.detail = detail;
    }



    public List<ImageSimpleDto> getImages() {
        return images;
    }

    public void setImages(List<ImageSimpleDto> images) {
        this.images = images;
    }

    public List<CompositionSimpleDto> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<CompositionSimpleDto> equipments) {
        this.equipments = equipments;
    }


    public RegionSimpleDto getRegion() {
        return region;
    }

    public void setRegion(RegionSimpleDto region) {
        this.region = region;
    }



}
