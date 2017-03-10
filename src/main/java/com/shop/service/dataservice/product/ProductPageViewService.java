package com.shop.service.dataservice.product;

import com.shop.service.dto.product.ProductViewDto;
import com.shop.web.viewmodel.PageViewItem;

import java.util.List;

/**
 * Created by zhirayrg on 3/9/2017.
 */
public interface ProductPageViewService {

    void addView(ProductViewDto viewDto);

    List<PageViewItem> getViewHistory(long productId);
}
