package com.shop.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhirayrg on 3/14/2017.
 */

@RestController
@RequestMapping(value = "/api")
public class DummyController {




    @GetMapping(value = "/dummy")
    public String getDummyMessage() {

        return "Hello World!";
    }
}
