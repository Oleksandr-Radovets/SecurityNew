package com.example.securitynew.repository;

import com.example.securitynew.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book save(Book book);
}
