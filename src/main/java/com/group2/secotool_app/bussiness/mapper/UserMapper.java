package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.request.UserRegistrationRequestDto;
import com.group2.secotool_app.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    User toUser (UserRegistrationRequestDto userRegistrationRequestDto);
}
