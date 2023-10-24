package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.UserDto;
import com.group2.secotool_app.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "userRole", target = "userRole")
    UserDto toUserDto (User user);
}
