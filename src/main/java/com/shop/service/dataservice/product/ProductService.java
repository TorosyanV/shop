package com.shop.service.dataservice.product;



import com.shop.data.entity.ProductEntity;
import com.shop.service.dto.product.ProductEditDto;
import com.shop.service.storage.ImageStorageException;
import com.shop.service.dto.product.ProductCreateDto;
import com.shop.service.dto.product.ProductWithDetailDto;

import java.util.List;

/**
 * Created by Vazgen on 08/17/2016.
 */
public interface ProductService {

    long add(ProductCreateDto product) throws ImageStorageException;

    void edit(ProductEditDto editDto, boolean isAdmin) throws ImageStorageException, InvalidProductOwnerException, ProductNotFoundException;

    ProductWithDetailDto getProductWithDetail(long id) throws ProductNotFoundException;

    List<ProductEntity> getAllByUserId(Long userId);

    ProductEntity getByIdAndUserId(Long id, Long userId) throws InvalidProductOwnerException, ProductNotFoundException;
    ProductEntity getById(Long id);


    void delete(Long productId, Long userId) throws InvalidProductOwnerException, ProductNotFoundException;

    Long getCountByOwnerRole(String role);

    long getAllCount();

    boolean isOwnerOfProduct(Long productId, Long userId);
}
