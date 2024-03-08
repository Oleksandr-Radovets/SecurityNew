package com.example.securitynew.repository;

import com.example.securitynew.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("FROM Book b WHERE b.categorySet =:categoryId" )
    List<Book> findAllByCategoryId(@Param("categoryId") Long categoryId);
    Book save(Book book);
}
