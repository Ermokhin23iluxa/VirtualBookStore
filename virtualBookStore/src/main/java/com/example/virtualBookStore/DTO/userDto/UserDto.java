package com.example.virtualBookStore.DTO.userDto;

import com.example.virtualBookStore.enums.Role;


public record UserDto(
        Long id,
        String name,
        String email,
        Role role
) {
}
