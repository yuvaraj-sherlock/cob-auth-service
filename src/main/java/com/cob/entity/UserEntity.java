package com.cob.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "COB_USER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
}
