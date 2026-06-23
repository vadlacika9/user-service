package com.userservice.dto.incoming;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInDto {

    private String email;

    private String password;
}