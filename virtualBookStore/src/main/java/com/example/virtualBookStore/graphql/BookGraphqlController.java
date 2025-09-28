package com.example.virtualBookStore.graphql;

import com.example.virtualBookStore.DTO.bookDto.BookDto;
import com.example.virtualBookStore.DTO.bookDto.CreateBookRequestDto;
import com.example.virtualBookStore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookGraphqlController {
    private final BookService bookService;


    @QueryMapping(name = "getAllBooks")
    public List<BookDto> getAllBooks(@Argument Integer limit, @Argument Integer offset){
        int l = (limit == null) ? 20 : limit;
        int o = (offset == null) ? 0 : offset;
        return bookService.getAllBooks(l,o);
    }
    @QueryMapping
    public List<BookDto> getAllBooksForCategory(
            @Argument String category,
            @Argument Integer limit,
            @Argument Integer offset
    ){
        int l = (limit == null) ? 20 : limit;
        int o = (offset == null) ? 0 : offset;
        return bookService.getAllBooksForCategory(category,l,o);
    }
    @QueryMapping
    public BookDto getOneBookById(@Argument Long id){
        return bookService.getOneBook(id);
    }
    @QueryMapping
    public List<BookDto> getAllBooksForAuthor(
            @Argument String author,
            @Argument Integer limit,
            @Argument Integer offset
    ){
        int l = (limit == null) ? 20 : limit;
        int o = (offset == null) ? 0 : offset;
        return bookService.getAllBooksForAuthor(author,l,o);
    }
    @MutationMapping
    public BookDto createBook(@Argument CreateBookInput createBookInput){
        CreateBookRequestDto requestDto = mapToCreateRequestDto(createBookInput);
        return bookService.createBook(requestDto);
    }
    public Boolean deleteBook(@Argument Long id){
        bookService.deleteBook(id);
        return true;
    }

    private CreateBookRequestDto mapToCreateRequestDto(CreateBookInput input) {
        CreateBookRequestDto dto = new CreateBookRequestDto();
        dto.setTitle(input.getTitle());
        dto.setAuthor(input.getAuthor());
        dto.setPrice(input.getPrice());
        dto.setStock(input.getStock());
        dto.setDescription(input.getDescription());
        dto.setCategoryIds(input.getCategoryIds());
        return dto;
    }

}
