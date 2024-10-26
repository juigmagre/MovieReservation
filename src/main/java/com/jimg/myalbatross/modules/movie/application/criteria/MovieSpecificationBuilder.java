package com.jimg.myalbatross.modules.movie.application.criteria;

import com.jimg.myalbatross.modules.movie.application.dto.MovieFilterRequest;
import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public class MovieSpecificationBuilder {
    public static Specification<Movie> createSpecification(MovieFilterRequest movieFilterRequest) {
        return getSpecifications(movieFilterRequest).reduce(Specification::and).orElse(Specification.where(null));
    }

    private static Stream<Specification<Movie>> getSpecifications(MovieFilterRequest movieFilterRequest) {
        return Stream.of(hasDirector(movieFilterRequest.getDirector()),
                        hasReleaseYear(movieFilterRequest.getReleaseYear()),
                        isAvailable())
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private static Optional<Specification<Movie>> isAvailable() {
        return Optional.of((root, query, criteriaBuilder) -> {
            Subquery<UUID> subquery = query.subquery(UUID.class);
            Root<Reservation> reservationRoot = subquery.from(Reservation.class);
            Join<Reservation, Movie> movieJoin = reservationRoot.join("movie");

            Predicate activeReservationPredicate = criteriaBuilder.and(
                    criteriaBuilder.isFalse(reservationRoot.get("cancelled")),
                    criteriaBuilder.lessThanOrEqualTo(reservationRoot.get("startDate"), LocalDateTime.now()),
                    criteriaBuilder.greaterThanOrEqualTo(reservationRoot.get("endDate"), LocalDateTime.now())
            );

            subquery.select(movieJoin.get("id"));
            subquery.where(activeReservationPredicate);

            return criteriaBuilder.not(root.get("id").in(subquery));
        });
    }

    private static Optional<Specification<Movie>> hasDirector(String director) {
        if (isNull(director)) {
            return Optional.empty();
        }
        return Optional.of((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("director"), "%" + director + "%"));
    }

    private static Optional<Specification<Movie>> hasReleaseYear(Long releaseYear) {
        if (isNull(releaseYear)) {
            return Optional.empty();
        }
        return Optional.of((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("releaseYear"), releaseYear));
    }
}
