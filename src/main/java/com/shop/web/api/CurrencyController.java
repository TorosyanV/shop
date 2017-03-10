package com.shop.web.api;

import com.shop.service.dataservice.currency.CurrencyService;
import com.shop.service.dto.currency.CurrencySimpleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhirayrg on 3/9/2017.
 */
@RestController
@RequestMapping(value = "/api")
public class CurrencyController {


    @Autowired
    private CurrencyService currencyService;

    @GetMapping(value = "/currencies")
    public List<CurrencySimpleDto> getCurrencies() {

        List<CurrencySimpleDto> currencies = currencyService.getAll();
        return currencies;
    }
}
