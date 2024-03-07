package com.example.securitynew.controller;

import com.example.securitynew.dto.book.BookResponseDto;
import com.example.securitynew.dto.book.CreateBookRequestDto;
import com.example.securitynew.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "book management", description = "Endpoints for managing books")
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "create book", description = "create book")
    @PostMapping("/book")
    public BookResponseDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.createBook(requestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get all books", description = "get all books")
    @GetMapping("/books")
    public List<BookResponseDto> bookAll() {
        return bookService.bookAll();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "find book by id", description = "find book by id")
    @GetMapping("book/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("book/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("book/{id}")
    public void updateBook(@PathVariable Long id,
                           @RequestBody CreateBookRequestDto createBookRequestDto) {
        bookService.updateById(id, createBookRequestDto);

    }

}
