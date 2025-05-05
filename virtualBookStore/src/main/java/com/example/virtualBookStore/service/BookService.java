package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.bookDto.BookDto;
import com.example.virtualBookStore.DTO.bookDto.CreateBookRequestDto;
import com.example.virtualBookStore.exceptions.NoSuchBookException;
import com.example.virtualBookStore.mapping.BookMapper;
import com.example.virtualBookStore.model.Book;
import com.example.virtualBookStore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public List<BookDto> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()){
            throw new NoSuchBookException("В системе нет книг!");
        }
        return books.stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    public BookDto createBook(CreateBookRequestDto createBookRequestDto){
        Book book = bookMapper.toBook(createBookRequestDto);
        book.setRating(0);

        Book savedBook = bookRepository.save(book);

        return bookMapper.toBookDto(savedBook);
    }

    public void deleteBook(Long bookId){
        this.checkBookExists(bookId);
        bookRepository.deleteById(bookId);
    }

    private void checkBookExists(Long bookId) {
        if(!bookRepository.existsById(bookId)){
            throw new NoSuchBookException("error.404.book.not_found");
        }
        log.info("book with id: {} exists",bookId);
    }
}
