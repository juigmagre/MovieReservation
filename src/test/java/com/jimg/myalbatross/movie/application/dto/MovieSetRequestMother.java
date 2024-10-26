package com.jimg.myalbatross.movie.application.dto;

import com.github.javafaker.Faker;
import com.jimg.myalbatross.modules.movie.application.dto.MovieSetRequest;

public class MovieSetRequestMother {
    public static MovieSetRequest random() {
        Faker faker = Faker.instance();
        return new MovieSetRequest(faker.name().title(), faker.name().fullName(),
                faker.number().randomNumber(),
                faker.number().randomDouble(2, 1, 10));
    }

    public static MovieSetRequest randomWithEmptyTitle() {
        Faker faker = Faker.instance();

        return new MovieSetRequest(
                "", faker.name().fullName(),
                faker.number().randomNumber(),
                faker.number().randomDouble(2, 1, 10));
    }

    public static MovieSetRequest randomWithNullTitle() {
        Faker faker = Faker.instance();

        return new MovieSetRequest(
                null, faker.name().fullName(),
                faker.number().randomNumber(),
                faker.number().randomDouble(2, 1, 10));
    }
}
