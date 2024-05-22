package com.example.krissbookservice.controller;

import com.example.krissbookservice.repository.AuthorRepository;
import com.example.krissbookservice.repository.BookRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Data
    @Builder
    public static class HealthDTO {
        private String state;
        private Integer booksCount;
        private Integer authorCount;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/health")
    public HealthDTO getHealth() {
        return HealthDTO
                .builder()
                .state("UP")
                .authorCount(authorRepository.findAll().size())
                .booksCount(bookRepository.findAll().size())
                .build();
    }
}
