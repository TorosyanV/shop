package com.shop.service.dto.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Vazgen on 08/24/2016.
 */
public class ProductCreateDto {


    private long bodyType;
    private long color;
    private Integer horsePower;
    private long mileage;

    private long fuelType;

    private Long gearType;

    private long model;

    private long city;
    private long user;
    private long capacity;
    private int year;
    private String userMessage;
    private String sellerPhone;
    private double price;
    private boolean customCleared;

    private List<Long> equipments;

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    private MultipartFile[] images;


    public boolean isCustomCleared() {
        return customCleared;
    }

    public void setCustomCleared(boolean customCleared) {
        this.customCleared = customCleared;
    }


    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public long getBodyType() {
        return bodyType;
    }

    public void setBodyType(long bodyType) {
        this.bodyType = bodyType;
    }

    public long getColor() {
        return color;
    }

    public void setColor(long color) {
        this.color = color;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public long getFuelType() {
        return fuelType;
    }

    public void setFuelType(long fuelType) {
        this.fuelType = fuelType;
    }

    public Long getGearType() {
        return gearType;
    }

    public void setGearType(Long gearType) {
        this.gearType = gearType;
    }

    public long getModel() {
        return model;
    }

    public void setModel(long model) {
        this.model = model;
    }

    public long getCity() {
        return city;
    }

    public void setCity(long city) {
        this.city = city;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

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

    public void setPrice(double price) {
        this.price = price;
    }



    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public List<Long> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Long> equipments) {
        this.equipments = equipments;
    }
}
