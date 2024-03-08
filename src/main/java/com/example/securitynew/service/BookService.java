package com.example.securitynew.service;

import com.example.securitynew.dto.book.BookDto;
import com.example.securitynew.dto.book.CreateBookRequestDto;
import java.util.List;

public interface BookService {

    BookDto createBook(CreateBookRequestDto bookRequestDto);

    List<BookDto> bookAll();

    BookDto findById(Long id);

    void updateById(Long id, CreateBookRequestDto createBookRequestDto);

    void delete(Long id);

    List<BookDto> findAllByCategoryId(Long categoryId);
}
