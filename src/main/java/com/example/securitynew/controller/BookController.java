package com.example.securitynew.controller;

import com.example.securitynew.dto.book.BookDto;
import com.example.securitynew.dto.book.BookSearchParameterDto;
import com.example.securitynew.dto.book.CreateBookRequestDto;
import com.example.securitynew.service.BookService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @PostMapping("/book")
    BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.createBook(requestDto);
    }

    @GetMapping("/books")
    List<BookDto> bookAll() {
        return bookService.bookAll();
    }

    @GetMapping("book/{id}")
    BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("book/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PutMapping("book/{id}")
    public void updateBook(@PathVariable Long id,
                           @RequestBody CreateBookRequestDto createBookRequestDto) {
        bookService.updateById(id, createBookRequestDto);

    }

    @GetMapping("/search")
    public List<BookDto> search(BookSearchParameterDto bookSearchParameterDto) {
        return bookService.search(bookSearchParameterDto);
    }
}
