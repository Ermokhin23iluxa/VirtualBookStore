package com.example.virtualBookStore.controller;

import com.example.virtualBookStore.DTO.reviewDto.CreateReviewRequestDto;
import com.example.virtualBookStore.DTO.reviewDto.ReviewResponseDto;
import com.example.virtualBookStore.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping()
    public ResponseEntity<List<ReviewResponseDto>> getAllReview(){
        return ResponseEntity.ok(reviewService.getAllReviews());
    }
    @GetMapping("/by-book/{bookId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewByBookId(@PathVariable Long bookId){
        return ResponseEntity.ok(reviewService.getReviewsForBookId(bookId));
    }
    @GetMapping("/by-title")
    public ResponseEntity<List<ReviewResponseDto>> getReviewByBookTittle(@RequestParam String title){
        return ResponseEntity.ok(reviewService.getReviewsForBookTittle(title));
    }
    @PostMapping()
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody @Valid CreateReviewRequestDto reviewRequestDto){
        ReviewResponseDto responseDto = reviewService.createReview(reviewRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
