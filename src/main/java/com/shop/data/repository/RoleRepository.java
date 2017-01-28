package com.shop.data.repository;


import com.shop.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vazgen on 12/20/16.
 */
public interface RoleRepository  extends JpaRepository<RoleEntity, Long> {


    RoleEntity findByName(String name);
}
