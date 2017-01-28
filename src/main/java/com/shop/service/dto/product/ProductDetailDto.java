package com.shop.service.dto.product;


import java.util.Date;

/**
 * Created by Vazgen on 08/25/2016.
 */
public class ProductDetailDto {

    private long mileage;

    private boolean isDemaged;
    private boolean customCleared;
    private int year;
    private String userMessage;
    private Double price;
    private boolean isImmediately;

    private String sellerPhone;
    private Date updated;

    private Date created;

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public boolean isDemaged() {
        return isDemaged;
    }

    public void setDemaged(boolean demaged) {
        isDemaged = demaged;
    }

    public boolean isCustomCleared() {
        return customCleared;
    }

    public void setCustomCleared(boolean customCleard) {
        this.customCleared = customCleard;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isImmediately() {
        return isImmediately;
    }

    public void setImmediately(boolean immediately) {
        isImmediately = immediately;
    }



    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
