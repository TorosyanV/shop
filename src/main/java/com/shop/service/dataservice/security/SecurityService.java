package com.shop.service.dataservice.security;

import com.shop.data.entity.UserEntity;

/**
 * Created by vazgen on 12/20/16.
 */
public interface SecurityService {
    String findLoggedInUsername();

    boolean autoLogin(String username, String password);

    long activateAccount(String token) throws InvalidTokenException;

    String generateActivationToken(long userId);

    String generateResetToken(long userId);

    boolean hasRole(String role);

    UserEntity getUserByResetToken(String token) throws InvalidTokenException;

    void changeUserPasswordByToken(String token, String password) throws InvalidTokenException;

    //boolean facebookLogin(String facebookId, String token) throws InvalidFacebookTokenException;
}