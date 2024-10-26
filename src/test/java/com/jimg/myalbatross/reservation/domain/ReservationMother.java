package com.jimg.myalbatross.reservation.domain;

import com.github.javafaker.Faker;
import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import com.jimg.myalbatross.movie.domain.MovieMother;
import com.jimg.myalbatross.user.domain.entity.UserMother;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ReservationMother {
    public static Reservation randomActiveWithMovie(Movie movie) {
        Faker faker = Faker.instance();

        return new Reservation(UUID.randomUUID(), UserMother.random(), movie, LocalDateTime.now(),
                faker.date().future(7, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDateTime(), false);
    }

    public static Reservation randomActive() {
        Faker faker = Faker.instance();

        return new Reservation(UUID.randomUUID(), UserMother.random(), MovieMother.random(), LocalDateTime.now(),
                faker.date().future(7, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDateTime(), false);
    }
}
