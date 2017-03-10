package com.shop.web.api;

import com.shop.service.analytic.dto.DayCountPair;
import com.shop.service.dataservice.product.ProductNotFoundException;
import com.shop.service.dataservice.product.ProductPageViewService;
import com.shop.service.dataservice.product.ProductService;
import com.shop.service.dataservice.user.UserService;
import com.shop.service.dto.product.ProductWithDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping(value = "/product/{id}/json")
    public ProductWithDetailDto getDetail(@PathVariable("id") Long productId) {

        ProductWithDetailDto productWithDetailDto = null;
        try {
            productWithDetailDto = productService.getProductWithDetail(productId);
        } catch (ProductNotFoundException e) {
            return null;
        }
        return productWithDetailDto;
    }


    @GetMapping(value = "/product/analytics/{id}")
    public List<DayCountPair> getProductAnalyticData(@PathVariable("id") Long productId, Principal principal) {

        boolean isOwner = productService.isOwnerOfProduct(productId, userService.findByUsername(principal.getName()).getId());

        if (!isOwner) {
            return null;
        }

        List<DayCountPair> viewHistory = productPageViewService.getViewHistory(productId);

        return viewHistory;

    }



}
