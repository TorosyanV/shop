package com.torter.service.search;


import com.torter.data.entity.ProductEntity;
import com.torter.data.specification.filter.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by zhiro on 11/23/16.
 */
public interface SearchService {

    Page<ProductEntity> search(SearchFilter filter, PageRequest pageRequest);
}
