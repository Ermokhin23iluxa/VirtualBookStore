package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.reviewDto.CreateReviewRequestDto;
import com.example.virtualBookStore.DTO.reviewDto.ReviewResponseDto;
import com.example.virtualBookStore.exceptions.NoSuchBookException;
import com.example.virtualBookStore.exceptions.NoSuchReviewException;
import com.example.virtualBookStore.mapping.ReviewMapper;
import com.example.virtualBookStore.model.Book;
import com.example.virtualBookStore.model.Review;
import com.example.virtualBookStore.repository.BookRepository;
import com.example.virtualBookStore.repository.ReviewRepository;
import jakarta.validation.Valid;
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

    public ReviewResponseDto createReview(CreateReviewRequestDto reviewRequestDto){
        Book book = bookRepository.findById(reviewRequestDto.getBookId())
                .orElseThrow(() -> new NoSuchBookException("Книга с id=" + reviewRequestDto.getBookId() + " не найдена"));

        Review review = reviewMapper.toReview(reviewRequestDto);
        review.setBook(book);
        Review savedReview = reviewRepository.save(review);

        BigDecimal newRating = calculateAverageRating(book.getId());
        book.setRating(newRating);
        bookRepository.save(book);

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

    private BigDecimal calculateAverageRating(Long bookId){
        List<Review> reviews = reviewRepository.findByBook_Id(bookId);
        BigDecimal sum = reviews.stream()
                .map(Review::getScore)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        return sum.divide(BigDecimal.valueOf(reviews.size()),2,RoundingMode.HALF_EVEN);
    }

    public List<ReviewResponseDto> getReviewsForBookTitle(String title) {
        Book book = bookRepository.findByTitleIgnoreCase(title)
                .orElseThrow(()->new NoSuchBookException("Книга с названием \"" + title + "\" не найдена"));

        List<Review> reviews = reviewRepository.findByBook_TitleIgnoreCase(title);
        if (reviews.isEmpty()) {
            throw new NoSuchReviewException("Нет отзывов для книги \"" + title + "\"");
        }
        return reviews.stream()
                .map(reviewMapper::toReviewResponseDto)
                .toList();
    }

    public List<ReviewResponseDto> getReviewsForBookId(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new NoSuchBookException("Книга с id=" + id + " не найдена"));

        List<Review> reviews = reviewRepository.findByBook_Id(id);
        if (reviews.isEmpty()) {
            throw new NoSuchReviewException("Нет отзывов для книги id=" + id);
        }
        return reviews.stream()
                .map(reviewMapper::toReviewResponseDto)
                .toList();
    }
}
