package com.torter.service.dataservice.user;


import com.torter.data.entity.UserEntity;
import com.torter.service.dto.user.UserCreateDto;

import java.util.List;

/**
 * Created by vazgen on 12/20/16.
 */
public interface UserService {
    long save(UserCreateDto user);

    UserEntity findByUsername(String username);
    UserEntity getById(Long id);

    List<UserEntity> getAll();

    Long getInactiveUserCount();
    Long getActiveUserCount();
}