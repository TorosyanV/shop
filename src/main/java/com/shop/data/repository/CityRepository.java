package com.shop.data.repository;

import com.shop.data.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Vazgen on 12-Nov-16.
 */
public interface CityRepository extends JpaRepository<CityEntity,Long> {
    List<CityEntity> findByRegionIdOrderByNameAsc(long regionId);
}
