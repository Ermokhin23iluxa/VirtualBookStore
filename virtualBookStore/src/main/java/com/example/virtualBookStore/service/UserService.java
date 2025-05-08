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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
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
                .toList();
    }
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
    @Transactional
    public void createUser(User user) {
//        if (userRepository.existsUserByName(user.getEmail())) {
//            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
//        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует");
        }
        save(user);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByEmail(username)
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


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }
}
