package com.example.virtualBookStore.controller;

import com.example.virtualBookStore.DTO.userDto.UserDto;
import com.example.virtualBookStore.model.User;
import com.example.virtualBookStore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDto = userService.getAllUsers();
        return ResponseEntity.ok(userDto);
    }
}
