package com.jimg.myalbatross.movie.domain;

import com.github.javafaker.Faker;
import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.movie.domain.vo.MovieTitle;
import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import com.jimg.myalbatross.reservation.domain.ReservationMother;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MovieMother {
    public static Movie random() {
        Faker faker = Faker.instance();

        return new Movie(UUID.randomUUID(),
                new MovieTitle(faker.name().title()), faker.name().fullName(),
                faker.number().randomNumber(),
                faker.number().randomDouble(2, 1, 10),
                new HashSet<>());
    }

    public static Movie randomWithId(UUID id) {
        Faker faker = Faker.instance();

        return new Movie(id,
                new MovieTitle(faker.name().title()), faker.name().fullName(),
                faker.number().randomNumber(),
                faker.number().randomDouble(2, 1, 10),
                new HashSet<>());
    }

    public static Movie randomNotAvailable() {
        Faker faker = Faker.instance();
        Movie movie = new Movie(UUID.randomUUID(),
                new MovieTitle(faker.name().title()), faker.name().fullName(),
                faker.number().randomNumber(),
                faker.number().randomDouble(2, 1, 10),
                new HashSet<>());
        ;
        Reservation activeReservation = ReservationMother.randomActiveWithMovie(movie);
        Set<Reservation> reservations = Set.of(activeReservation);
        movie.setReservations(reservations);
        return movie;
    }
}
