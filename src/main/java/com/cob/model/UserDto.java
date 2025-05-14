package com.cob.model;

 /*Lombok is not used here because MapStruct was unable to detect the generated
 getters and setters. Therefore, they are added manually to ensure proper mapping.*/

public class UserDto {
    private String userName;
    private String password;

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
}
