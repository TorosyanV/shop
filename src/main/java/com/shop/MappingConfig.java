package com.shop;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by Vazgen on 08/03/2016.
 */



@Configuration
@ComponentScan(basePackages={"com.carshop"})
public class MappingConfig {

    @Bean(name = "dozerBean")
    public DozerBeanMapper configDozer() throws IOException {
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }

}