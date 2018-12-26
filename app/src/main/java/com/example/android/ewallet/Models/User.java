package com.example.android.ewallet.Models;

import android.graphics.Bitmap;
//LOUDA TEST
public class User {
    private String firstName;
    private String secondName;
    private String userName;
    private String email;
    private String password;
    private String pinCode;
    private String ssn;
    private Bitmap profilePic;
    private Wallet wallet;

    public User(String firstName, String secondName, String userName, String email, String password, String pinCode, String ssn, Bitmap profilePic) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.pinCode = pinCode;
        this.ssn = ssn;
        this.profilePic = profilePic;
        wallet = new Wallet();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getSsn() {
        return ssn;
    }

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }
}

