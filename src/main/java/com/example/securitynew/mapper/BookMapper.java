package com.example.securitynew.mapper;

import com.example.securitynew.config.MapperConfig;
import com.example.securitynew.dto.book.BookDto;
import com.example.securitynew.dto.book.CreateBookRequestDto;
import com.example.securitynew.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookRequestDto);
}
