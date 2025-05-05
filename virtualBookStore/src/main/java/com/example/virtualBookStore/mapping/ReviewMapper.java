package com.example.virtualBookStore.mapping;

import com.example.virtualBookStore.DTO.CreateReviewRequestDto;
import com.example.virtualBookStore.DTO.ReviewResponseDto;
import com.example.virtualBookStore.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    ReviewResponseDto toReviewResponseDto(Review review);
    Review toReview(CreateReviewRequestDto createReviewRequestDto);
}
