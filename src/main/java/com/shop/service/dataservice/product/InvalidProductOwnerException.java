package com.shop.service.dataservice.product;

/**
 * Created by Vazgen on 06-Jan-17.
 */
public class InvalidProductOwnerException extends Exception {
    InvalidProductOwnerException(String message, Throwable cause) {
        super(message, cause);
    }

    InvalidProductOwnerException(String message) {
        super(message);
    }
}
