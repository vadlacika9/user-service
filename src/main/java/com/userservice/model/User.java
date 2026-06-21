package com.sportappuserservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name="users")
@ToString(callSuper=true)
public class User extends BaseEntity{

    @Column(name = "username",nullable = false, unique = true, length = 25)
    private String username;
    @Column
    private String firstName;
    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String password;
}