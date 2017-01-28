package com.shop.web.viewmodel;

import org.hibernate.validator.constraints.Email;

/**
 * Created by vazgen on 12/20/16.
 */
public class RegistrationModel {

    @Email
    private String userName;
    private String password;
    private String confirmPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}

