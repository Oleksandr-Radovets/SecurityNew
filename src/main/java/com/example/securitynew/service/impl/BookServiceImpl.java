package com.example.securitynew.service.impl;

import com.example.securitynew.dto.book.BookDto;
import com.example.securitynew.dto.book.BookSearchParameterDto;
import com.example.securitynew.dto.book.CreateBookRequestDto;
import com.example.securitynew.mapper.BookMapper;
import com.example.securitynew.model.Book;
import com.example.securitynew.repository.BookRepository;
import com.example.securitynew.repository.criteria.book.BookSpecificationBuilder;
import com.example.securitynew.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private BookSpecificationBuilder builder;

    @Override
    public BookDto createBook(CreateBookRequestDto bookRequestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(bookRequestDto)));
    }

    @Override
    public List<BookDto> bookAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id" + id)));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> search(BookSearchParameterDto bookSearchParameterDto) {
        Specification<Book> build = builder.build(bookSearchParameterDto);
        return bookRepository.findAll(build).stream()
                .map(bookMapper::toDto).toList();
    }

    @Override
    public void updateById(Long id, CreateBookRequestDto createBookRequestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find book " + id));
        Book book1 = bookMapper.toModel(createBookRequestDto);
        book1.setId(id);
        bookRepository.save(book1);
    }

}
