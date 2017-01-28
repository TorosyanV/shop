package com.shop.data.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vazgen on 8/13/16.
 */
@Entity
@Table(name = "productdetail")
public class ProductDetailEntity {


    @GenericGenerator(name = "cardetail_carid", strategy = "foreign", parameters = @Parameter(name = "property", value = "car"))
    @Id
    @GeneratedValue(generator = "cardetail_carid")
    @Column(name = "car_id", unique = true, nullable = false)
    private Long carId;

    @Column(nullable = false)
    private Date created;

    private Date updated;


    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ProductEntity car;

    @Column(name = "mileage",nullable = false)
    private long mileage;

    @Column(name = "damaged", nullable = false)
    private boolean damaged;

    @Column(name = "customCleared", nullable = false)
    private boolean customCleared;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "userMessage",nullable = true)
    private String userMessage;

    @Column(name = "sellerPhone", nullable = false)
    private String sellerPhone;


    @Column(name = "price", nullable = true)
    private Double price;

    @Column(name = "immediately", nullable = false)
    private boolean immediately;


    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public ProductEntity getCar() {
        return car;
    }

    public void setCar(ProductEntity car) {
        this.car = car;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public boolean isCustomCleared() {
        return customCleared;
    }

    public void setCustomCleared(boolean customCleared) {
        this.customCleared = customCleared;
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

    public boolean isImmediately() {
        return immediately;
    }

    public void setImmediately(boolean immediately) {
        this.immediately = immediately;
    }


    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }


    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
