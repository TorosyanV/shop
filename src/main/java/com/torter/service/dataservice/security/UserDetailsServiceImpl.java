package com.torter.service.dataservice.security;

import com.torter.data.entity.RoleEntity;
import com.torter.data.entity.UserEntity;
import com.torter.data.repository.UserRepository;
import com.torter.service.dataservice.user.ShopUser;
import com.torter.service.dataservice.user.ShopUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by vazgen on 12/20/16.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public ShopUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(userName);
        logger.info(String.format("Trying load user by userName : %s", userName));
        if (userEntity == null) {
            logger.info(String.format("User not found userName : %s", userName));
            throw new UsernameNotFoundException(String.format("User not found with this username %s", userName));
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleEntity role : userEntity.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new ShopUser(userEntity.getUserName(), userEntity.getPasswordHash(), grantedAuthorities, userEntity.isActive());

    }
}