package com.shop.data.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vazgen on 8/13/16.
 */

@Entity
@Table(name = "country")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private Set<RegionEntity> regions;

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

    public Set<RegionEntity> getRegions() {
        return regions;
    }

    public void setRegions(Set<RegionEntity> regions) {
        this.regions = regions;
    }
}
