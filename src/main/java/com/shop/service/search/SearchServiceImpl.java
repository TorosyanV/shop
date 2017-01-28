package com.shop.service.search;


import com.shop.data.entity.ProductEntity;
import com.shop.data.specification.filter.SearchFilter;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhiro on 11/23/16.
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = Logger.getLogger(SearchServiceImpl.class.getName());
    @Autowired
    private Mapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<ProductEntity> search(SearchFilter filter, PageRequest pageRequest) {

        return null;

    }

}
