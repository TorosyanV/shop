package com.shop.data.repository;

import com.shop.data.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vazgen on 07-Jan-17.
 */
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
