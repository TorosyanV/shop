package com.shop.data.repository;

import com.shop.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by vazgen on 12/20/16.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
    List<UserEntity> findAllByJoinCodeAndJoinCodeNotNullOrderByIdDesc(String joinCode);
    Long countByActive(boolean active);

    List<UserEntity> findAllByJoinCode(String joinCode);
    List<UserEntity> findAllByFacebookUserIsTrue();
}
