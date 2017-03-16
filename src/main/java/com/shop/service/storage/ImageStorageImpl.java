package com.shop.service.storage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhirayrg on 3/14/2017.
 */
@Component
public class ImageStorageImpl implements ImageStorage {
    @Override
    public void save(MultipartFile file, String name, String extension) throws ImageStorageException {

    }
}
