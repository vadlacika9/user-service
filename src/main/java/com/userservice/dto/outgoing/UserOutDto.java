package com.userservice.dto.outgoing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutDto {
    private Long id;
    private String uuid;
    private String email;
    private String role;
    private String token;
    private String username;
}
