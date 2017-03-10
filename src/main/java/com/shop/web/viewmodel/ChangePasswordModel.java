package com.shop.web.viewmodel;

/**
 * Created by zhirayrg on 3/10/2017.
 */

public class ChangePasswordModel {

    private String password;
    private String confirmPassword;


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