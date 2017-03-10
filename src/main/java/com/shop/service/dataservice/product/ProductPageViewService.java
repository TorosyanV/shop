package com.shop.service.dataservice.product;

import com.shop.service.analytic.dto.DayCountPair;
import com.shop.service.dto.product.ProductViewDto;

import java.util.List;

/**
 * Created by zhirayrg on 3/9/2017.
 */
public interface ProductPageViewService {

    void addView(ProductViewDto viewDto);

    List<DayCountPair> getViewHistory(long productId);
}
