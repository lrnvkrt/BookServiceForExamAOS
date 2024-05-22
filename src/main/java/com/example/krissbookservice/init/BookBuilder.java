package com.example.krissbookservice.init;

import com.example.krissbookservice.model.Author;
import com.example.krissbookservice.model.Book;
import com.example.krissbookservice.repository.AuthorRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class BookBuilder {
    private AuthorRepository authorRepository;
    private Faker faker;
    private final Random random = new Random();

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    public List<Book> build(int quantity) {
        List<Author> authors = authorRepository.findAll();

        return IntStream.range(0, quantity)
                .mapToObj(index -> Book
                        .builder()
                        .genre(faker.book().genre())
                        .name(faker.book().title())
                        .author(authors.get(random.nextInt(authors.size())))
                        .publisher(faker.book().publisher())
                        .publicationDate(faker.date().past(30000, TimeUnit.DAYS))
                        .build())
                .collect(Collectors.toList());
    }
}
