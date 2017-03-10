package com.shop.web.controller;

import com.shop.service.dataservice.product.ProductNotFoundException;
import com.shop.service.dataservice.product.ProductPageViewService;
import com.shop.service.dataservice.product.ProductService;
import com.shop.service.dataservice.user.UserService;
import com.shop.service.dto.product.ProductViewDto;
import com.shop.service.dto.product.ProductWithDetailDto;
import com.shop.service.search.SearchServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Optional;

/**
 * Created by zhirayrg on 3/10/2017.
 */

@Controller
public class DetailController {


    private static final Logger logger = Logger.getLogger(SearchServiceImpl.class.getName());

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPageViewService productPageViewService;

    @Autowired
    private UserService userService;

    @GetMapping("/detail/{productId}")
    public String getProductDetail(@PathVariable("productId") Long productId, ModelMap model, Device device, Principal principal) throws ProductNotFoundException {


        ProductWithDetailDto productWithDetailDto = null;

        productWithDetailDto = productService.getProductWithDetail(productId);
        addViewCount(productId, principal);

        if (productWithDetailDto.getDetail().getUserMessage() == null) {
            productWithDetailDto.getDetail().setUserMessage(productWithDetailDto.getDescription());
        }
        model.addAttribute("product", productWithDetailDto);
        model.addAttribute("device", device);
        return "detail";

    }

    private void addViewCount(long productId, Principal principal) {

        Optional<Long> loggedInUserId= Optional.empty();
        if (principal != null) {
            loggedInUserId = Optional.of(userService.findByUsername(principal.getName()).getId());
        }

        ProductViewDto productViewDto = new ProductViewDto();
        productViewDto.setProductId(productId);
        productViewDto.setUserId(loggedInUserId);
        productPageViewService.addView(productViewDto);
    }
}
