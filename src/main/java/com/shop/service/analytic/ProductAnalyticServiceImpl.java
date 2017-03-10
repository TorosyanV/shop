package com.shop.service.analytic;

import com.shop.data.entity.ProductDetailEntity;
import com.shop.data.repository.ProductDetailRepository;
import com.shop.service.analytic.dto.DayCountPair;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhirayrg on 3/10/2017.
 */
public class ProductAnalyticServiceImpl implements ProductAnalyticService {

    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public List<DayCountPair> productsHistory() {

        List<ProductDetailEntity> all = productDetailRepository.findAll();

        return convertToDayCountPair(all);
    }

    private List<DayCountPair> convertToDayCountPair(List<ProductDetailEntity> products) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return products.stream().collect(Collectors.groupingBy(c -> df.format(c.getCreated()), Collectors.counting())).entrySet().stream().map(x -> new DayCountPair(x.getKey(), x.getValue())).collect(Collectors.toList());
    }
}
