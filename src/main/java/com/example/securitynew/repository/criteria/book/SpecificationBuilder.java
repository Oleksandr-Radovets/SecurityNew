package com.example.securitynew.repository.criteria.book;

import com.example.securitynew.dto.book.BookSearchParameterDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParameterDto bookSearchParameterDto);

}
