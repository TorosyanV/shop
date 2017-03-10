package com.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhirayrg on 3/10/2017.
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @GetMapping("/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("/500")
    public String error500() {
        return "error/500";
    }

    @GetMapping("/productnotfound")
    public String productnotfound() {
        return "error/productnotfound";
    }



}
