package com.shop.service.dataservice.currency;


import com.shop.service.dto.currency.CurrencySimpleDto;

import java.util.List;

/**
 * Created by vazgen on 11/21/16.
 */
public interface CurrencyService {

    List<CurrencySimpleDto> getAll();
}
