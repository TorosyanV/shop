package com.shop.service.dto.user;

/**
 * Created by vazgen on 12/20/16.
 */
public class UserCreateDto {
    private String userName;
    private String password;
    private String joinCode;

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

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }
}
