package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.categoryDto.CategoryResponseDto;
import com.example.virtualBookStore.DTO.categoryDto.CreateCategoryRequestDto;
import com.example.virtualBookStore.mapping.CategoryMapper;
import com.example.virtualBookStore.model.Category;
import com.example.virtualBookStore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResponseDto create(CreateCategoryRequestDto dto) {
        Category category = categoryMapper.toCategory(dto);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toCategoryResponseDto(saved);
    }

    @Cacheable(value = "categories")
    public List<CategoryResponseDto> listAllCategory() {
        log.info("Кэш listAllCategory MISS — загружаем из БД");
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponseDto)
                .toList();
    }
}
