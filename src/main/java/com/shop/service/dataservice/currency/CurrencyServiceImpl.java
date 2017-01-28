package com.shop.service.dataservice.currency;


import com.shop.data.entity.CurrencyEntity;
import com.shop.data.repository.CurrencyRepository;
import com.shop.helper.DozerExtension;
import com.shop.service.dto.currency.CurrencySimpleDto;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vazgen on 11/21/16.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private Mapper mapper;
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CurrencySimpleDto> getAll() {

        List<CurrencyEntity> currencyEntities = currencyRepository.findAll();
        List<CurrencySimpleDto> currencySimpleDtos = DozerExtension.map(mapper, currencyEntities, CurrencySimpleDto.class);
        return currencySimpleDtos;
    }
}
