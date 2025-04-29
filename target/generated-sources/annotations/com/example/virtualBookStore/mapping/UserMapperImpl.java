package com.example.virtualBookStore.mapping;

import com.example.virtualBookStore.DTO.userDto.RegisterUserResponseDto;
import com.example.virtualBookStore.DTO.userDto.UserDto;
import com.example.virtualBookStore.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-29T20:15:23+0300",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public RegisterUserResponseDto toRegisterUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterUserResponseDto registerUserResponseDto = new RegisterUserResponseDto();

        return registerUserResponseDto;
    }

    @Override
    public User toUser(RegisterUserResponseDto registerUserResponseDto) {
        if ( registerUserResponseDto == null ) {
            return null;
        }

        User user = new User();

        return user;
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String password = null;

        id = user.getId();
        name = user.getName();
        password = user.getPassword();

        UserDto userDto = new UserDto( id, name, password );

        return userDto;
    }
}
