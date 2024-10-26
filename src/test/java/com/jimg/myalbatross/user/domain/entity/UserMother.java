package com.jimg.myalbatross.user.domain.entity;

import com.github.javafaker.Faker;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.modules.user.domain.vo.UserBirth;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class UserMother {
    public static User random() {
        Faker faker = Faker.instance();
        return new User(UUID.randomUUID(), faker.internet().emailAddress(),
                faker.name().fullName(),
                new UserBirth(faker.date()
                        .between(
                                Date.from(LocalDate.now().minusYears(90).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                Date.from(LocalDate.now().minusYears(18).atStartOfDay(ZoneId.systemDefault()).toInstant())
                        )
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()));
    }

    public static User randomWithId(UUID id) {
        Faker faker = Faker.instance();
        return new User(id, faker.internet().emailAddress(),
                faker.name().fullName(),
                new UserBirth(faker.date()
                        .between(
                                Date.from(LocalDate.now().minusYears(90).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                Date.from(LocalDate.now().minusYears(18).atStartOfDay(ZoneId.systemDefault()).toInstant())
                        )
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()));
    }

    public static User randomWithMail(String mail) {
        Faker faker = Faker.instance();
        return new User(UUID.randomUUID(), mail,
                faker.name().fullName(),
                new UserBirth(faker.date()
                        .between(
                                Date.from(LocalDate.now().minusYears(90).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                Date.from(LocalDate.now().minusYears(18).atStartOfDay(ZoneId.systemDefault()).toInstant())
                        )
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()));
    }
}
