package com.example.krissbookservice.controller;

import com.example.krissbookservice.exception.NotFoundException;
import com.example.krissbookservice.model.Book;
import com.example.krissbookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BooksController {
    private BookRepository bookRepository;
    private final String entityName = "book";

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Book getBookById(@PathVariable UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException(entityName, "id", id.toString()));
    }

    @GetMapping("/author/id/{id}")
    public List<Book> getBooksByAuthorId(@PathVariable UUID id) {
        return bookRepository.findAllByAuthorId(id);
    }

    @GetMapping("/country/{country}")
    public List<Book> getBooksByAuthorsCountry(@PathVariable String country) {
        return bookRepository.findAllByAuthorCountry(country);
    }
}
