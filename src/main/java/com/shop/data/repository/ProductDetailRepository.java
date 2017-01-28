package com.shop.data.repository;

import com.shop.data.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vazgen on 05-Jan-17.
 */


public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long> {


}
