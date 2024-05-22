package com.example.krissbookservice.init;

import com.example.krissbookservice.model.Author;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class AuthorBuilder {
    private Faker faker;
    @Autowired
    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    public List<Author> build(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(index ->
                        Author.builder()
                                .name(faker.name().name())
                                .middleName(faker.name().firstName())
                                .lastName(faker.name().lastName())
                                .country(faker.country().name())
                                .build()).collect(Collectors.toList()
                );

    }
}
