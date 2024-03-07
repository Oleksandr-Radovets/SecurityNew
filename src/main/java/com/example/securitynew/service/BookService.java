package com.example.securitynew.service;

import com.example.securitynew.dto.book.BookResponseDto;
import com.example.securitynew.dto.book.CreateBookRequestDto;
import java.util.List;

public interface BookService {

    BookResponseDto createBook(CreateBookRequestDto bookRequestDto);

    List<BookResponseDto> bookAll();

    BookResponseDto findById(Long id);

    void updateById(Long id, CreateBookRequestDto createBookRequestDto);

    void delete(Long id);

    List<BookResponseDto> findAllByCategoryId(Long categoryId);
}
