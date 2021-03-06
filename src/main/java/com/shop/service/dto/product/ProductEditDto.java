package com.shop.service.dto.product;

import java.util.List;

/**
 * Created by Vazgen on 05-Jan-17.
 */
public class ProductEditDto extends ProductCreateDto {

    private Long productId;


    private List<Long> oldImages;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Long> getOldImages() {
        return oldImages;
    }

    public void setOldImages(List<Long> oldImages) {
        this.oldImages = oldImages;
    }
}
