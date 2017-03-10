package com.shop.service.analytic.dto;

/**
 * Created by zhirayrg on 3/10/2017.
 */

public class DayCountPair {

    private String date;
    private Long count;


    public DayCountPair(String date, Long count) {
        this.date = date;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
