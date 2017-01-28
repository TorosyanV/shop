package com.shop.service.dataservice.user;


import com.shop.data.entity.UserEntity;
import com.shop.data.repository.RoleRepository;
import com.shop.data.repository.UserRepository;
import com.shop.service.dto.user.UserCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by vazgen on 12/20/16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public long save(UserCreateDto request) {
        UserEntity user = new UserEntity();
        user.setUserName(request.getUserName());
        user.setPasswordHash(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAll() {

        return userRepository.findAll();
    }

    @Override
    public Long getInactiveUserCount() {
        return userRepository.countByActive(false);
    }

    @Override
    public Long getActiveUserCount() {
        return userRepository.countByActive(true);
    }

}
