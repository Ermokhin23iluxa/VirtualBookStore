package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.bookDto.BookDto;
import com.example.virtualBookStore.DTO.bookDto.CreateBookRequestDto;
import com.example.virtualBookStore.exceptions.NoSuchBookException;
import com.example.virtualBookStore.mapping.BookMapper;
import com.example.virtualBookStore.model.Book;
import com.example.virtualBookStore.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;


    public List<BookDto> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()){
            throw new NoSuchBookException("В системе пока нет книг!");
        }
        return books.stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    // ФИЛЬТРАЦИИ
    public List<BookDto> getAllBooksForAuthor(String author){
        List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
        if(books.isEmpty()){
            throw new NoSuchBookException("Книги автора \"" + author + "\" не найдены");
        }
        return books.stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    public List<BookDto> getAllBooksForCategory(String nameCategory){
        List<Book> books = bookRepository.findByCategories_NameIgnoreCase(nameCategory);
        if(books.isEmpty()){
            throw new NoSuchBookException("Книги в категории \"" + nameCategory + "\" не найдены");
        }
        return books.stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    public BookDto getOneBook(Long id){
        Book b = bookRepository.findById(id)
                .orElseThrow(()->new NoSuchBookException("Книга с id=" + id + " не найдена"));
        return bookMapper.toBookDto(b);
    }

    public BookDto createBook(@Valid CreateBookRequestDto createBookRequestDto){
        Book book = bookMapper.toBook(createBookRequestDto);
        book.setRating(BigDecimal.valueOf(1));

        Book savedBook = bookRepository.save(book);

        return bookMapper.toBookDto(savedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NoSuchBookException("Книга с id=" + id + " не найдена");
        }
        bookRepository.deleteById(id);
    }

//    private void checkBookExists(Long bookId) {
//        if(!bookRepository.existsById(bookId)){
//            throw new NoSuchBookException("Книга в системе не найдена!");
//        }
//        log.info("Книга с id: {} существует",bookId);
//    }
}
