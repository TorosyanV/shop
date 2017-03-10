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


    @GenericGenerator(name = "productdetail_productid", strategy = "foreign", parameters = @Parameter(name = "property", value = "product"))
    @Id
    @GeneratedValue(generator = "productdetail_productid")
    @Column(name = "product_id", unique = true, nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Date created;

    private Date updated;


    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ProductEntity product;

    @Column(name = "userMessage",nullable = true)
    private String userMessage;

    @Column(name = "sellerPhone", nullable = false)
    private String sellerPhone;


    @Column(name = "price", nullable = true)
    private Double price;

    @Column(name = "immediately", nullable = false)
    private boolean immediately;


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
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
