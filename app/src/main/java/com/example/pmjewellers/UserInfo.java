package com.example.pmjewellers;

public class UserInfo {
    String UserName,UserEmail,UserPassword,UserComfirmPassword,UserId;

    public UserInfo(String userName, String userEmail, String userPassword, String userComfirmPassword, String userId) {
        UserName = userName;
        UserEmail = userEmail;
        UserPassword = userPassword;
        UserComfirmPassword = userComfirmPassword;
        UserId = userId;
    }

    public UserInfo(String userName, String userEmail, String userId) {
        UserName = userName;
        UserEmail = userEmail;
        UserId = userId;
    }
}
