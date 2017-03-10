package com.shop.data.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vazgen on 8/9/16.
 */
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;




    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private ProductDetailEntity detail;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "productComposition", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "compositionId"))
    private List<CompositionEntity> compositions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId", nullable = false)
    private CityEntity city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;


    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ImageEntity> images;

    @ManyToOne
    @JoinColumn(name = "mainImageId")
    private ImageEntity mainImage;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;



    public ImageEntity getMainImage() {
        return mainImage;
    }

    public void setMainImage(ImageEntity mainImage) {
        this.mainImage = mainImage;
    }

    public ProductDetailEntity getDetail() {
        return detail;
    }

    public void setDetail(ProductDetailEntity detail) {
        this.detail = detail;
    }

    public void setCompositions(List<CompositionEntity> compositions) {
        this.compositions = compositions;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<CompositionEntity> getCompositions() {
        return this.compositions;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
