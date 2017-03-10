package com.shop.data.repository;

import com.shop.data.entity.ProductPageViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhirayrg on 3/10/2017.
 */
public interface ProductPageViewRepository extends JpaRepository<ProductPageViewEntity,Long> {


    List<ProductPageViewEntity> findAllByProductId(long productId);
}
