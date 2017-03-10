package com.shop.web.controller;

import com.shop.data.entity.ProductEntity;
import com.shop.service.dataservice.product.ProductService;
import com.shop.service.dataservice.user.UserService;
import com.shop.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by zhirayrg on 3/10/2017.
 */
@Controller
public class HomeController {


    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MailService mailService;


    @RequestMapping("/")
    public String index(ModelMap model) {
        return "home";

    }

    @GetMapping("/myoffers")
    public String myOffers(ModelMap modelMap, Principal principal) {

        Long userId = userService.findByUsername(principal.getName()).getId();
        List<ProductEntity> userProducts = productService.getAllByUserId(userId);
        modelMap.addAttribute("products", userProducts);
        return "myoffers";

    }

}
