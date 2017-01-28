package com.shop.data.repository;

import com.shop.data.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Vazgen on 08/17/2016.
 */
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long>, JpaSpecificationExecutor {

    List<ProductEntity> findAllByUserIdAndDeletedIsFalse(Long userId, Pageable pageable);

    ProductEntity findByIdAndUserIdAndDeletedIsFalse(Long id, Long userId);

    ProductEntity findByIdAndDeletedIsFalse(Long id);

    Long countByUserRolesName(String roleName);
}
