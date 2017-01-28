package com.torter.service.dataservice.currency;


import com.torter.data.entity.CurrencyEntity;
import com.torter.data.repository.CurrencyRepository;
import com.torter.helper.DozerExtension;
import com.torter.service.dto.currency.CurrencySimpleDto;
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
