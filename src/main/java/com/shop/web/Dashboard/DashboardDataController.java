package com.shop.web.Dashboard;

import com.shop.service.analytic.ProductAnalyticService;
import com.shop.service.analytic.UserAnalyticService;
import com.shop.service.analytic.dto.DayCountPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value = "/user/analytics/{joinCode}")
    public List<DayCountPair> getUsersByJoinCode(@PathVariable("joinCode") String joinCode) {

        return userAnalyticService.operatorsRegisteredUsersHistory(joinCode);

    }

    @GetMapping(value = "/user/analytics/facebookusers")
    public List<DayCountPair> getFacebookUsers() {

        return userAnalyticService.facebookRegisteredUsersHistory();

    }

    @GetMapping(value = "/user/analytics/allusers")
    public List<DayCountPair> getAllUsers() {

        return userAnalyticService.allRegisteredHistory();

    }


    @GetMapping(value = "/product/analytics")
    public List<DayCountPair> getAllProducts() {

        return productAnalyticService.productsHistory();

    }




}