package com.shop.service.analytic;

import com.shop.data.entity.UserEntity;
import com.shop.data.repository.UserRepository;
import com.shop.service.analytic.dto.DayCountPair;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhirayrg on 3/10/2017.
 */
public class UserAnalyticServiceImpl implements UserAnalyticService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DayCountPair> operatorsRegisteredUsersHistory(String joinCode) {

        List<UserEntity> users = userRepository.findAllByJoinCode(joinCode);

        return convertToRegisteredUserCountItems(users);
    }

    @Override
    public List<DayCountPair> facebookRegisteredUsersHistory() {
        List<UserEntity> allByFacebookUserIsNotNull = userRepository.findAllByFacebookUserIsTrue();

        return convertToRegisteredUserCountItems(allByFacebookUserIsNotNull);
    }

    @Override
    public List<DayCountPair> allRegisteredHistory() {
        List<UserEntity> all = userRepository.findAll();
        return convertToRegisteredUserCountItems(all);
    }

    private List<DayCountPair> convertToRegisteredUserCountItems(List<UserEntity> users) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return users.stream().collect(Collectors.groupingBy(u -> df.format(u.getCreated()), Collectors.counting())).entrySet().stream().map(x -> new DayCountPair(x.getKey(), x.getValue())).collect(Collectors.toList());
    }
}
