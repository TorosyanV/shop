package com.shop.data.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vazgen on 8/13/16.
 */
@Entity
@Table(name = "region")
public class RegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId", nullable = false)
    private CountryEntity country;

    @Column(name = "name",nullable =false )
    private String name;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "region")
    private Set<CityEntity> cities;


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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public Set<CityEntity> getCities() {
        return cities;
    }

    public void setCities(Set<CityEntity> cities) {
        this.cities = cities;
    }
}
