package com.jimg.myalbatross.movie.application.dto;

import com.github.javafaker.Faker;
import com.jimg.myalbatross.modules.movie.application.dto.MovieCreateRequest;

import java.util.UUID;

public class MovieCreateRequestMother {
    public static MovieCreateRequest random() {
        Faker faker = Faker.instance();

        return new MovieCreateRequest(UUID.randomUUID(),
                faker.name().title(), faker.name().fullName(),
                faker.number().randomNumber(),
                faker.number().randomDouble(2, 1, 10));
    }

    public static MovieCreateRequest randomWithNullTitle() {
        Faker faker = Faker.instance();

        return new MovieCreateRequest(UUID.randomUUID(),
                null, faker.name().fullName(),
                faker.number().randomNumber(),
                faker.number().randomDouble(2, 1, 10));
    }

    public static MovieCreateRequest randomWithEmptyTitle() {
        Faker faker = Faker.instance();

        return new MovieCreateRequest(UUID.randomUUID(),
                "", faker.name().fullName(),
                faker.number().randomNumber(),
                faker.number().randomDouble(2, 1, 10));
    }
}
