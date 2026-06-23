package com.userservice.dto.incoming;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRegisterDto {


    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
