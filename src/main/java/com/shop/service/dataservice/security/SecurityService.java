package com.shop.service.dataservice.security;

/**
 * Created by vazgen on 12/20/16.
 */
public interface SecurityService {
    String findLoggedInUsername();

    boolean autoLogin(String username, String password);

    long activateAccount(String token) throws InvalidTokenException;

    String generateActivationToken(long userId);
     boolean hasRole(String role);
}