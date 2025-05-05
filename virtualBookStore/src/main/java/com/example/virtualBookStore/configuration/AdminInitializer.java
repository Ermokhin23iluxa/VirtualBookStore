package com.example.virtualBookStore.configuration;

import com.example.virtualBookStore.enums.Role;
import com.example.virtualBookStore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminInitializer {
    @Value("${app.admins}")
    private List<String> adminEmails;
    private final UserRepository userRepository;

    public void initAdmins(){
        for(String email:adminEmails){
            userRepository.findByEmail(email)
                    .ifPresent(user -> {
                        user.setRole(Role.ADMIN);
                        userRepository.save(user);
                    });
        }
    }
}
