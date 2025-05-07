package com.example.virtualBookStore.mapping;

import com.example.virtualBookStore.DTO.categoryDto.CategoryResponseDto;
import com.example.virtualBookStore.DTO.categoryDto.CreateCategoryRequestDto;
import com.example.virtualBookStore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryResponseDto toCategoryResponseDto(Category category);
    Category toCategory(CreateCategoryRequestDto categoryRequestDto);
}
