package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.bookDto.BookDto;
import com.example.virtualBookStore.DTO.bookDto.CreateBookRequestDto;
import com.example.virtualBookStore.exceptions.NoSuchBookException;
import com.example.virtualBookStore.exceptions.NoSuchCategoryException;
import com.example.virtualBookStore.mapping.BookMapper;
import com.example.virtualBookStore.model.Book;
import com.example.virtualBookStore.model.Category;
import com.example.virtualBookStore.repository.BookRepository;
import com.example.virtualBookStore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;


    @Cacheable(value = "books")
    public List<BookDto> getAllBooks(){
        log.info("Кэш getAllBooks MISS — загружаем из БД");
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
        Category category = categoryRepository.findByNameIgnoreCase(nameCategory)
                .orElseThrow(()->new NoSuchCategoryException("Категория \"" + nameCategory + "\" не найдена"));

        List<Book> books = bookRepository.findByCategories_NameIgnoreCase(nameCategory);
        if(books.isEmpty()){
            throw new NoSuchBookException("В категории \"" + nameCategory + "\" пока нет книг");
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

    @CacheEvict(value = "books", allEntries = true)
    public BookDto createBook(CreateBookRequestDto createBookRequestDto){
        Book book = bookMapper.toBook(createBookRequestDto);
        book.setRating(BigDecimal.ZERO);
        book.setStock(createBookRequestDto.getStock());
        List<Category> cats = categoryRepository.findAllById(createBookRequestDto.getCategoryIds());
        book.setCategories(cats);
        Book savedBook = bookRepository.save(book);

        return bookMapper.toBookDto(savedBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NoSuchBookException("Книга с id=" + id + " не найдена");
        }
        bookRepository.deleteById(id);
    }

}
