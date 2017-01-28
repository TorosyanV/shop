package com.shop.data.entity;

import javax.persistence.*;

/**
 * Created by vazgen on 8/13/16.
 */
@Entity
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carId", nullable = false)
    private ProductEntity car;

    @Column(name = "imageName", nullable = false)
    private String imageName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getCar() {
        return car;
    }

    public void setCar(ProductEntity car) {
        this.car = car;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {

        return "https://vuvustorage.blob.core.windows.net/images/" + imageName + ".jpg";
    }
}
