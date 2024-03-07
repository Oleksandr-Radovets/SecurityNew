package com.example.securitynew.service.impl;

import com.example.securitynew.dto.book.BookResponseDto;
import com.example.securitynew.dto.book.CreateBookRequestDto;
import com.example.securitynew.mapper.BookMapper;
import com.example.securitynew.model.Book;
import com.example.securitynew.repository.BookRepository;
import com.example.securitynew.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponseDto createBook(CreateBookRequestDto bookRequestDto) {
        Book save = bookRepository.save(bookMapper.toEntity(bookRequestDto));
        return bookMapper.toDto(save);
    }

    @Override
    public List<BookResponseDto> bookAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookResponseDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id" + id)));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDto> findAllByCategoryId(Long categoryId) {
        return bookRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public void updateById(Long id, CreateBookRequestDto createBookRequestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find book " + id));
        Book book1 = bookMapper.toEntity(createBookRequestDto);
        book1.setId(id);
        bookRepository.save(book1);
    }
}
