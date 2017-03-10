package com.shop.web.viewmodel;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by zhirayrg on 3/9/2017.
 */
public class ProductForSellCreateRequest {

    @NotNull
    private String userMessage;

    @NotNull
    private Double price;

    @NotNull
    private Long city;

    private String sellerPhone;

    private List<Long> compositions;

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public List<Long> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Long> compositions) {
        this.compositions = compositions;
    }
}
