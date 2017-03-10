package com.shop.service.analytic;

import com.shop.service.analytic.dto.DayCountPair;

import java.util.List;

/**
 * Created by zhirayrg on 3/10/2017.
 */
public interface UserAnalyticService {

    List<DayCountPair> operatorsRegisteredUsersHistory(String joinCode);
    List<DayCountPair> facebookRegisteredUsersHistory();
    List<DayCountPair> allRegisteredHistory();
}
