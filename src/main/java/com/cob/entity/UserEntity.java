package com.cob.entity;

import jakarta.persistence.*;

 /*Lombok is not used here because MapStruct was unable to detect the generated
 getters and setters. Therefore, they are added manually to ensure proper mapping.*/

@Entity
@Table(name = "COB_USER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
