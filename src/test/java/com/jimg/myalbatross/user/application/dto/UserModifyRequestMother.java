package com.jimg.myalbatross.user.application.dto;

import com.github.javafaker.Faker;
import com.jimg.myalbatross.modules.user.application.dto.UserModifyRequest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserModifyRequestMother {
    public static UserModifyRequest random() {
        Faker faker = Faker.instance();

        return new UserModifyRequest(faker.internet().emailAddress(),
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

    public static UserModifyRequest randomWithMail(String mail) {
        Faker faker = Faker.instance();

        return new UserModifyRequest(mail,
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
