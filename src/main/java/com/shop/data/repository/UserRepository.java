package com.shop.data.repository;

import com.shop.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vazgen on 12/20/16.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
    Long countByActive(boolean active);
}
