package com.shop;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by vazgen on 1/27/17.
 */
@ControllerAdvice
@Controller
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());



    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){

        logger.error(e.getMessage(),e);
        return "redirect:/error/500";
    }


}