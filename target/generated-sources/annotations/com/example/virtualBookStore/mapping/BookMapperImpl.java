package com.example.virtualBookStore.mapping;

import com.example.virtualBookStore.DTO.bookDto.BookDto;
import com.example.virtualBookStore.DTO.bookDto.CreateBookRequestDto;
import com.example.virtualBookStore.model.Book;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-29T20:15:24+0300",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        Long id = null;
        String tittle = null;
        String author = null;
        Double price = null;
        String description = null;
        Double rating = null;

        id = book.getId();
        tittle = book.getTittle();
        author = book.getAuthor();
        price = book.getPrice();
        description = book.getDescription();
        rating = book.getRating();

        BookDto bookDto = new BookDto( id, tittle, author, price, description, rating );

        return bookDto;
    }

    @Override
    public Book toBook(CreateBookRequestDto createBookRequestDto) {
        if ( createBookRequestDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setTittle( createBookRequestDto.tittle() );
        book.setAuthor( createBookRequestDto.author() );
        book.setPrice( createBookRequestDto.price() );
        book.setDescription( createBookRequestDto.description() );

        return book;
    }
}
