package com.shop.web.Dashboard;

import com.shop.service.analytic.ProductAnalyticService;
import com.shop.service.analytic.UserAnalyticService;
import com.shop.service.analytic.dto.DayCountPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhirayrg on 3/10/2017.
 */

@RestController
@RequestMapping(value = "/dashboard/api")
public class DashboardDataController {

    @Autowired
    private UserAnalyticService userAnalyticService;



    @Autowired
    private ProductAnalyticService productAnalyticService;


    @RequestMapping(value = "/user/analytics/{joinCode}", method = RequestMethod.GET)
    public List<DayCountPair> getUsersByJoinCode(@PathVariable("joinCode") String joinCode) {

        return userAnalyticService.operatorsRegisteredUsersHistory(joinCode);

    }

    @RequestMapping(value = "/user/analytics/facebookusers", method = RequestMethod.GET)
    public List<DayCountPair> getFacebookUsers() {

        return userAnalyticService.facebookRegisteredUsersHistory();

    }

    @RequestMapping(value = "/user/analytics/allusers", method = RequestMethod.GET)
    public List<DayCountPair> getAllUsers() {

        return userAnalyticService.allRegisteredHistory();

    }


    @RequestMapping(value = "/product/analytics", method = RequestMethod.GET)
    public List<DayCountPair> getAllProducts() {

        return productAnalyticService.productsHistory();

    }




}