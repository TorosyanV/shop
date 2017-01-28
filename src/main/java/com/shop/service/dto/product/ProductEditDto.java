package com.shop.service.dto.product;

import java.util.List;

/**
 * Created by Vazgen on 05-Jan-17.
 */
public class ProductEditDto extends ProductCreateDto {

    private Long carId;


    private List<Long> oldImages;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public List<Long> getOldImages() {
        return oldImages;
    }

    public void setOldImages(List<Long> oldImages) {
        this.oldImages = oldImages;
    }
}
