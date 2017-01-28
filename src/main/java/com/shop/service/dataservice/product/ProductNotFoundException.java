package com.shop.service.dataservice.product;

/**
 * Created by Vazgen on 06-Jan-17.
 */
public class ProductNotFoundException extends Exception {
    ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    ProductNotFoundException(String message) {
        super(message);
    }
}
