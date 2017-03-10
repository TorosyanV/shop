package com.shop.web.viewmodel;

/**
 * Created by zhirayrg on 3/9/2017.
 */
public class PageViewItem {

    private String date;
    private Long viewCount;

    public PageViewItem(String date, Long viewCount) {
        this.date = date;
        this.viewCount = viewCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }
}
