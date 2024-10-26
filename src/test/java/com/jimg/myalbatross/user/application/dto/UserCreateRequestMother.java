package com.jimg.myalbatross.user.application.dto;

import com.github.javafaker.Faker;
import com.jimg.myalbatross.modules.user.application.dto.UserCreateRequest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class UserCreateRequestMother {
    public static UserCreateRequest random() {
        Faker faker = Faker.instance();

        return new UserCreateRequest(UUID.randomUUID(), faker.internet().emailAddress(),
                faker.name().fullName(),
                faker.date()
                        .between(
                                Date.from(LocalDate.now().minusYears(90).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                Date.from(LocalDate.now().minusYears(18).atStartOfDay(ZoneId.systemDefault()).toInstant())
                        )
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
    }

    public static UserCreateRequest randomWithIncorrectBirthDate() {
        Faker faker = Faker.instance();

        return new UserCreateRequest(UUID.randomUUID(), null,
                faker.name().fullName(),
                faker.date()
                        .between(
                                Date.from(LocalDate.now().minusYears(17).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                Date.from(LocalDate.now().minusYears(0).atStartOfDay(ZoneId.systemDefault()).toInstant())
                        )
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
    }

    public static UserCreateRequest randomWithEmail(String email) {
        Faker faker = Faker.instance();

        return new UserCreateRequest(UUID.randomUUID(), email,
                faker.name().fullName(),
                faker.date()
                        .between(
                                Date.from(LocalDate.now().minusYears(90).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                Date.from(LocalDate.now().minusYears(18).atStartOfDay(ZoneId.systemDefault()).toInstant())
                        )
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
    }
}
