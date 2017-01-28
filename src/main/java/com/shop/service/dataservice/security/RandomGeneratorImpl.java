package com.shop.service.dataservice.security;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Created by Vazgen on 06-Jan-17.
 */
@Service
public class RandomGeneratorImpl implements RandomGenerator {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    @Override
    public String generateString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
