package com.example.virtualBookStore.mapping;

import com.example.virtualBookStore.DTO.auth.RegisterUserRequestDto;
import com.example.virtualBookStore.DTO.userDto.UserDto;
import com.example.virtualBookStore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    //RegisterUserResponseDto toRegisterUserResponseDto(User user);

    //@Mapping(target = "password",ignore = true)
    User toUser(RegisterUserRequestDto registerUserRequestDto);
    UserDto toUserDto(User user);
}
