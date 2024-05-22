package com.example.krissbookservice.repository;

import com.example.krissbookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findAllByAuthorCountry(String country);
    List<Book> findAllByAuthorId(UUID uuid);
}
