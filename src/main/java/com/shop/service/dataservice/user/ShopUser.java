package com.shop.service.dataservice.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by Vazgen on 06-Jan-17.
 */
public class ShopUser extends User implements ShopUserDetails {

    private boolean active;

    public ShopUser(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean active) {
        super(username, password, authorities);
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
