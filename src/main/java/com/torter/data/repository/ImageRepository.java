package com.torter.data.repository;

import com.torter.data.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vazgen on 07-Jan-17.
 */
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
