package com.shop.service.dto.product;

import java.util.Optional;

/**
 * Created by zhirayrg on 3/9/2017.
 */
public class ProductViewDto {

    private long productId;
    private Optional<Long> userId;

    private String referral;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Optional<Long> getUserId() {
        return userId;
    }

    public void setUserId(Optional<Long> userId) {
        this.userId = userId;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }
}
