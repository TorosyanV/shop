package com.shop.service.search;


import com.shop.data.entity.ProductEntity;
import com.shop.data.specification.filter.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by zhiro on 11/23/16.
 */
public interface SearchService {

    Page<ProductEntity> search(SearchFilter filter, PageRequest pageRequest);
}
