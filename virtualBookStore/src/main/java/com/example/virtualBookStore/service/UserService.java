package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.bookDto.BookDto;
import com.example.virtualBookStore.DTO.userDto.UserDto;
import com.example.virtualBookStore.exceptions.NoSuchBookException;
import com.example.virtualBookStore.mapping.BookMapper;
import com.example.virtualBookStore.mapping.UserMapper;
import com.example.virtualBookStore.model.Book;
import com.example.virtualBookStore.model.User;
import com.example.virtualBookStore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new NoSuchBookException("В системе нет пользователей!");
        }
        return users.stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

}
