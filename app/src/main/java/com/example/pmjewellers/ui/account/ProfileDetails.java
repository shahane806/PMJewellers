package com.example.pmjewellers.ui.account;


public class ProfileDetails {
    private String name,address,email,profilePicture,mobile;

    ProfileDetails(String name,String address,String mobile,String email,String profilePicture){
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.profilePicture = profilePicture;

    }
    public ProfileDetails(){}

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }
}
