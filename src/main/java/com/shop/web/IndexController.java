package com.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by vazgen on 1/27/17.
 */
@Controller
public class IndexController {



    @GetMapping("")
    public String index()


    {
        return "index";
    }
}
