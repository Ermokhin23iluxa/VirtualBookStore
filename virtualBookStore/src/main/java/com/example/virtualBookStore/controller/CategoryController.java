package com.example.virtualBookStore.controller;

import com.example.virtualBookStore.DTO.categoryDto.CategoryResponseDto;
import com.example.virtualBookStore.DTO.categoryDto.CreateCategoryRequestDto;
import com.example.virtualBookStore.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(@RequestBody @Valid CreateCategoryRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> listAll() {
        return ResponseEntity.ok(categoryService.listAllCategory());
    }
}
