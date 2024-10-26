package com.jimg.myalbatross.reservation.application.dto;

import com.github.javafaker.Faker;
import com.jimg.myalbatross.modules.reservation.application.dto.ReservationCreateRequest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ReservationCreateRequestMother {
    public static ReservationCreateRequest random() {
        Faker faker = Faker.instance();
        return new ReservationCreateRequest(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
                faker.date().future(7, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
    }

    public static ReservationCreateRequest randomWithExceededDays() {
        Faker faker = Faker.instance();
        return new ReservationCreateRequest(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
                faker.date().between(
                                Date.from(LocalDate.now().plusDays(8).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                                Date.from(LocalDate.now().plusYears(200).atStartOfDay(ZoneId.systemDefault()).toInstant())).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
    }

    public static ReservationCreateRequest randomWithUserIdAndMovieId(UUID userId, UUID movieId) {
        Faker faker = Faker.instance();
        return new ReservationCreateRequest(UUID.randomUUID(), userId, movieId,
                faker.date().future(7, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
    }
}
