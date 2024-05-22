package com.example.krissbookservice.controller;

import com.example.krissbookservice.exception.NotFoundException;
import com.example.krissbookservice.model.Author;
import com.example.krissbookservice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private AuthorRepository authorRepository;
    private final String entityName = "author";

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Author getAuthorById(@PathVariable UUID id) {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundException(entityName, "id", id.toString()));
    }

    @GetMapping("/country/{country}")
    public List<Author> getAuthorByCountry(@PathVariable String country) {
        return authorRepository.findAllByCountry(country);
    }
}
