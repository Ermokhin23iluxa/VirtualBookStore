package com.example.virtualBookStore.controller;

import com.example.virtualBookStore.DTO.bookDto.BookDto;
import com.example.virtualBookStore.DTO.bookDto.CreateBookRequestDto;
import com.example.virtualBookStore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> booksDto = bookService.getAllBooks();
        return ResponseEntity.ok(booksDto);
    }
    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> getAllBooksForAuthor(
            @RequestParam("author") String author
    ){
        List<BookDto> booksDto = bookService.getAllBooksForAuthor(author);
        return ResponseEntity.ok(booksDto);
    }
    @GetMapping("/by-category")
    public ResponseEntity<List<BookDto>> getAllBooksForCategory(
            @RequestParam("category") String category
    ){
        List<BookDto> booksDto = bookService.getAllBooksForCategory(category);
        return ResponseEntity.ok(booksDto);
    }


    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid CreateBookRequestDto createBookRequestDto) throws BindException {
        BookDto createdBookDto = bookService.createBook(createBookRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookDto);
    }

    @DeleteMapping("/{Id}/delete")
    public ResponseEntity<Void> deleteBook(@PathVariable Long Id){
        bookService.deleteBook(Id);
        log.info("Book with id: {} successfully deleted",Id);
        return ResponseEntity.noContent().build();
    }
}
