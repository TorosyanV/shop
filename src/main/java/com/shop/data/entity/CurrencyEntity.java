package com.shop.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by vazgen on 11/21/16.
 */
@Entity
@Table(name = "currency")
public class CurrencyEntity {

    @Id
    private long id;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "isoCode", nullable = false)
    private String isoCode;

    private double rate;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
}
