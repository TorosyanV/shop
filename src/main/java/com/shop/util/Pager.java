package com.shop.util;

/**
 * Created by Vazgen on 05-Dec-16.
 */
public class Pager {

    int totalItems;
    int currentPage;
    int pageSize;
    int totalPages;
    int startPage;
    int endPage;
    private String url;


    public Pager(int totalItems, int page, int pageSize, String url) {
        this.url=url;
        // calculate total, start and end pages
        int totalPages = (int) Math.ceil((double) totalItems / (double) pageSize);
        int currentPage = page;//???
        int startPage = currentPage - 5;
        int endPage = currentPage + 4;
        if (startPage <= 0) {
            endPage -= (startPage - 1);
            startPage = 1;
        }
        if (endPage > totalPages) {
            endPage = totalPages;
            if (endPage > 10) {
                startPage = endPage - 9;
            }
        }
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalItems = totalPages;
        this.startPage = startPage;
        this.endPage = endPage;
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}