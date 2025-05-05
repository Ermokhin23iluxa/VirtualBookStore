package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.CreateReviewRequestDto;
import com.example.virtualBookStore.DTO.ReviewResponseDto;
import com.example.virtualBookStore.DTO.bookDto.BookDto;
import com.example.virtualBookStore.exceptions.NoSuchBookException;
import com.example.virtualBookStore.exceptions.NoSuchReviewException;
import com.example.virtualBookStore.mapping.ReviewMapper;
import com.example.virtualBookStore.model.Book;
import com.example.virtualBookStore.model.Review;
import com.example.virtualBookStore.repository.BookRepository;
import com.example.virtualBookStore.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final BookRepository bookRepository;

    public ReviewResponseDto addReview(CreateReviewRequestDto reviewRequestDto){
        Review review = reviewMapper.toReview(reviewRequestDto);
        Review savedReview = reviewRepository.save(review);
        Book book = bookRepository.getBookById(review.getBook().getId());
        book.setRating(newScore(review));
        return reviewMapper.toReviewResponseDto(savedReview);
    }

    public List<ReviewResponseDto> getAllReviews(){
        List<Review> reviews = reviewRepository.findAll();
        if(reviews.isEmpty()){
            throw new NoSuchReviewException("В системе нет отзывов!");
        }
        return reviews.stream()
                .map(reviewMapper::toReviewResponseDto)
                .collect(Collectors.toList());
    }
    private BigDecimal newScore(Review review){
        Book oldBook = bookRepository.getBookById(review.getBook().getId());
        return oldBook.getRating().add(review.getScore())
                .divide(BigDecimal.valueOf(2),2, RoundingMode.HALF_EVEN);
    }

}
