package com.shop.service.dataservice.user;


import com.shop.service.dto.user.UserCreateDto;
import com.shop.data.entity.UserEntity;

import java.util.List;

/**
 * Created by vazgen on 12/20/16.
 */
public interface UserService {

    long save(UserCreateDto user);
    //UserEntity save(FacebookUserDto facebookUserDto);

    UserEntity findByUsername(String username);
    UserEntity getById(Long id);

    List<UserEntity> getAll();
    List<UserEntity> getAllByJoinCode(String joinCode);

    Long getInactiveUserCount();
    Long getActiveUserCount();
}