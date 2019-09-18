package com.example.administrator.myapplicationapp.db;

import java.io.Serializable;

public class UserInformation  implements Serializable {
    private String userPhone = null;
    private String userImagePath = null;

    public UserInformation(String userPhone) {
        this.userPhone = userPhone;
    }

    public UserInformation(String userPhone, String userImagePath) {
        this.userPhone = userPhone;
        this.userImagePath = userImagePath;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserImagePath() {
        return userImagePath;
    }
}
