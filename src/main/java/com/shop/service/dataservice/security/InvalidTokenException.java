package com.shop.service.dataservice.security;

/**
 * Created by Vazgen on 07-Jan-17.
 */


public class InvalidTokenException extends Exception {

    InvalidTokenException(String message) {
        super(message);
    }
}

