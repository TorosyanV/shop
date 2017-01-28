package com.shop.service.dataservice.user;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Vazgen on 06-Jan-17.
 */
public interface ShopUserDetails extends UserDetails {

    boolean isActive();
}
