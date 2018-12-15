package com.hsh.model;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = -4845734727331908354L;

    private String userId;

    private String userName;

    private String province;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
