package com.example.pmjewellers;

public class UserInfo {
    String UserName,UserEmail,UserId;

    public UserInfo() {
    }

    public UserInfo(String userName, String userEmail, String userId) {
        UserName = userName;
        UserEmail = userEmail;
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
