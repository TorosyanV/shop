package com.torter.service.dataservice.product;



import com.torter.data.entity.ProductEntity;
import com.torter.service.dto.product.ProductCreateDto;
import com.torter.service.dto.product.ProductEditDto;
import com.torter.service.dto.product.ProductWithDetailDto;
import com.torter.service.storage.ImageStorageException;

import java.util.List;

/**
 * Created by Vazgen on 08/17/2016.
 */
public interface ProductService {

    long add(ProductCreateDto car) throws ImageStorageException;

    void edit(ProductEditDto editDto, boolean isAdmin) throws ImageStorageException, InvalidProductOwnerException;

    ProductWithDetailDto getCarWithDetail(long id) throws ProductNotFoundException;

    List<ProductEntity> getAllByUserId(Long userId);

    ProductEntity getByIdAndUserId(Long id, Long userId) throws InvalidProductOwnerException;
    ProductEntity getById(Long id);


    void delete(Long carId, Long userId) throws InvalidProductOwnerException;

    Long getCountByOwnerRole(String role);

    long getAllCount();
}
