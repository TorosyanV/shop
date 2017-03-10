package com.shop.web.viewmodel;

import java.util.List;

/**
 * Created by zhirayrg on 3/9/2017.
 */
public class ProductForSellUpdateRequest {

    private Long productId;

    private List<Long> oldImages;

    private String userMessage;

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

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
