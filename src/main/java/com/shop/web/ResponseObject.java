package com.shop.web;

import org.springframework.http.HttpStatus;

/**
 * Created by zhirayrg on 3/14/2017.
 */
public class ResponseObject {

    private HttpStatus status;
    private String message;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
