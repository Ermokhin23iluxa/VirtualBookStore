package com.example.virtualBookStore.mapping;


import com.example.virtualBookStore.DTO.bookDto.BookDto;
import com.example.virtualBookStore.DTO.bookDto.CreateBookRequestDto;
import com.example.virtualBookStore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    BookDto toBookDto(Book book);
    Book toBook(CreateBookRequestDto createBookRequestDto);
}
