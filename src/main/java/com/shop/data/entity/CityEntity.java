package com.shop.data.entity;

import javax.persistence.*;

/**
 * Created by vazgen on 8/13/16.
 */
@Entity
@Table(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regionId", nullable = false)
    private RegionEntity region;

    @Column(name = "village", nullable = false)
    private boolean village;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public boolean isVillage() {
        return village;
    }

    public void setVillage(boolean village) {
        this.village = village;
    }
}
