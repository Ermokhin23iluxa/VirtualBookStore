package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.userDto.UserDto;
import com.example.virtualBookStore.exceptions.NoSuchBookException;
import com.example.virtualBookStore.exceptions.UserAlreadyExistException;
import com.example.virtualBookStore.exceptions.UserNotFoundException;
import com.example.virtualBookStore.mapping.UserMapper;
import com.example.virtualBookStore.model.User;
import com.example.virtualBookStore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new NoSuchBookException("В системе нет пользователей!");
        }
        return users.stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
    @Transactional
    public void createUser(User user) {

        if (userRepository.existsUserByName(user.getUsername())) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует");
        }
        save(user);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findUserByName(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

//    @Transactional(readOnly = true)
//    public User getCurrentUser() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        return getUserByUsername(username);
//    }

    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }
}
