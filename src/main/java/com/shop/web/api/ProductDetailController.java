package com.shop.web.api;

import com.shop.service.dataservice.product.ProductNotFoundException;
import com.shop.service.dataservice.product.ProductPageViewService;
import com.shop.service.dataservice.product.ProductService;
import com.shop.service.dataservice.user.UserService;
import com.shop.service.dto.product.ProductWithDetailDto;
import com.shop.web.viewmodel.PageViewItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by zhirayrg on 3/9/2017.
 */
@RestController
@RequestMapping(value = "/api")
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPageViewService productPageViewService;

    @Autowired
    UserService userService;



    @RequestMapping(value = "/product/{id}/json", method = RequestMethod.GET)
    public ProductWithDetailDto getDetail(@PathVariable("id") Long productId) {

        ProductWithDetailDto productWithDetailDto = null;
        try {
            productWithDetailDto = productService.getProductWithDetail(productId);
        } catch (ProductNotFoundException e) {
            return null;
        }
        return productWithDetailDto;
    }


    @RequestMapping(value = "/product/analytics/{id}", method = RequestMethod.GET)
    public List<PageViewItem> getProductAnalyticData(@PathVariable("id") Long productId, Principal principal) {

        boolean isOwner = productService.checkOwnerOfProduct(productId, userService.findByUsername(principal.getName()).getId());

        if (!isOwner) {
            return null;
        }

        List<PageViewItem> viewHistory = productPageViewService.getViewHistory(productId);

        return viewHistory;

    }



}
