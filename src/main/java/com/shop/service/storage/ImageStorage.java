package com.shop.service.storage;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by vazgen on 11/30/16.
 */
public interface ImageStorage {

     void save(MultipartFile file, String name, String extension) throws ImageStorageException;
}
