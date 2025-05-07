package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.categoryDto.CategoryResponseDto;
import com.example.virtualBookStore.DTO.categoryDto.CreateCategoryRequestDto;
import com.example.virtualBookStore.mapping.CategoryMapper;
import com.example.virtualBookStore.model.Category;
import com.example.virtualBookStore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponseDto create(CreateCategoryRequestDto dto) {
        Category category = categoryMapper.toCategory(dto);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toCategoryResponseDto(saved);
    }
    public List<CategoryResponseDto> listAllCategory() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponseDto)
                .toList();
    }
}
