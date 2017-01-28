package com.shop.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;

/**
 * Created by vazgen on 11/30/16.
 */
public class ImageUtil {

    public static BufferedImage resize(BufferedImage imageToResize, int targetSize) {
        return Scalr.resize(imageToResize, targetSize);
    }

    public static String generateRandomImageName(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
