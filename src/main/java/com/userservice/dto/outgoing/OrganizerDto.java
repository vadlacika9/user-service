package com.userservice.dto.outgoing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizerDto {
    private String username;
    private String email;
    private String reliabilityScore;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}

