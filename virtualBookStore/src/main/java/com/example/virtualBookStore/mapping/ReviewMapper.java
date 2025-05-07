package com.example.virtualBookStore.mapping;

import com.example.virtualBookStore.DTO.reviewDto.CreateReviewRequestDto;
import com.example.virtualBookStore.DTO.reviewDto.ReviewResponseDto;
import com.example.virtualBookStore.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    @Mapping(target = "bookId",source = "book.id")
    @Mapping(target = "bookTittle",source = "book.tittle")
    @Mapping(target = "userName",source = "user.name")
    ReviewResponseDto toReviewResponseDto(Review review);

    Review toReview(CreateReviewRequestDto createReviewRequestDto);
}
