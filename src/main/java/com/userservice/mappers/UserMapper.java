package com.userservice.mappers;

import com.userservice.dto.incoming.UserRegisterDto;
import com.userservice.dto.outgoing.OrganizerDto;
import com.userservice.dto.outgoing.SimpleUserInfoOutDto;
import com.userservice.dto.outgoing.UserOutDto;
import com.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRegistrationDtoToUser(UserRegisterDto userRegistrationDto);
    UserOutDto userToUserOutDto(User user);
    OrganizerDto userToOrganizerDto(User user);
    SimpleUserInfoOutDto userToSimpleUserInfoDto(User user);


}
