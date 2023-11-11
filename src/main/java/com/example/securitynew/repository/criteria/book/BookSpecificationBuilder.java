package com.example.securitynew.repository.criteria.book;

import com.example.securitynew.dto.book.BookSearchParameterDto;
import com.example.securitynew.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
     public Specification<Book> build(BookSearchParameterDto bookSearchParameterDto) {
        Specification<Book> specification = Specification.where(null);
        if (bookSearchParameterDto != null && bookSearchParameterDto.author().length > 0) {
            specification = specification
                    .and(bookSpecificationProviderManager
                            .getSpecificationProvider("author")
                            .getSpecification(bookSearchParameterDto.author()));
        }
        if (bookSearchParameterDto.title() != null && bookSearchParameterDto.title().length > 0) {
            specification = specification
                    .and(bookSpecificationProviderManager
                            .getSpecificationProvider("title")
                            .getSpecification(bookSearchParameterDto.title()));
        }
        return specification;
    }

}
