package com.example.virtualBookStore.controller;

import com.example.virtualBookStore.DTO.userDto.UserDto;
import com.example.virtualBookStore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDto = userService.getAllUsers();
        return ResponseEntity.ok(userDto);
    }
//    @PostMapping
//    public ResponseEntity<?> createUser(
//            @Valid @RequestBody UserDto dto,
//            BindingResult bindingResult   // ← обязательно сразу после @Valid-параметра
//    )
//    {
//        if (bindingResult.hasErrors()) { // если привязка к модели не произошла
//            // Собираем все ошибки в список
//            List<String> errors = bindingResult.getFieldErrors().stream()
//                    .map(err -> err.getField() + ": " + err.getDefaultMessage())
//                    .collect(Collectors.toList());
//
//            // Формируем единый объект-ответ
//            Map<String, Object> response = new HashMap<>();
//            response.put("status", HttpStatus.BAD_REQUEST.value());
//            response.put("errors", errors);
//
//            return ResponseEntity.badRequest().body(response);
//            // Если валидация прошла — вызываем сервис
//            //userService.createUser(dto);
//            return ResponseEntity.ok().build();
//        }
//    }
}
