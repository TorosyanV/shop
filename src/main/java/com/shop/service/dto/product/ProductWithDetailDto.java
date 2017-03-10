package com.shop.service.dto.product;


import com.shop.service.ProductDescription;
import com.shop.service.dto.ImageSimpleDto;
import com.shop.service.dto.location.RegionSimpleDto;
import com.shop.service.dto.CompositionSimpleDto;
import com.shop.service.dto.ImageUrl;
import com.shop.service.dto.location.CitySimpleDto;

import java.util.List;

/**
 * Created by Vazgen on 08/25/2016.
 */
public class ProductWithDetailDto implements ProductDescription {


    private  long productId;
    private List<ImageSimpleDto> images;
    private ImageUrl mainImage;
    private ProductDetailDto detail;
    private RegionSimpleDto region;
    private CitySimpleDto city;

    private List<CompositionSimpleDto> compositions;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public List<CompositionSimpleDto> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<CompositionSimpleDto> compositions) {
        this.compositions = compositions;
    }


    public RegionSimpleDto getRegion() {
        return region;
    }

    public void setRegion(RegionSimpleDto region) {
        this.region = region;
    }


    @Override
    public String getDescription() {
        return String.format("User Message about this product is %s", getDetail().getUserMessage());
    }
}
