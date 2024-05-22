package com.example.krissbookservice.init;

import com.example.krissbookservice.model.Author;
import com.example.krissbookservice.model.Book;
import com.example.krissbookservice.repository.AuthorRepository;
import com.example.krissbookservice.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class DataBaseInitializer implements CommandLineRunner {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private AuthorBuilder authorBuilder;
    private BookBuilder bookBuilder;

    @Autowired
    public void setAuthorBuilder(AuthorBuilder authorBuilder) {
        this.authorBuilder = authorBuilder;
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookBuilder(BookBuilder bookBuilder) {
        this.bookBuilder = bookBuilder;
    }

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        log.info("Генерация 10_000 авторов с случайным числом книг (от 0 до 5)");
        List<Author> authors = authorBuilder.build(10000);
        log.info("Генерация завершена, добавление в репозиторий");
        authorRepository.saveAll(authors);
        log.info("Добавление завершено");
        List<Book> books = bookBuilder.build(100000);
        bookRepository.saveAll(books);
    }
}
