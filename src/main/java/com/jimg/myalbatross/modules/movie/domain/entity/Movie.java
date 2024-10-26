package com.jimg.myalbatross.modules.movie.domain.entity;

import com.jimg.myalbatross.modules.movie.domain.vo.MovieTitle;
import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Movie {
    @Id
    private UUID id;
    @Embedded
    private MovieTitle title;
    private String director;
    private Long releaseYear;
    private Double duration;
    @OneToMany(mappedBy = "movie")
    private Set<Reservation> reservations;

    public Movie(UUID id, MovieTitle title, String director, Long year, Double duration) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseYear = year;
        this.duration = duration;
    }

    public String getTitle() {
        return title.value();
    }

    public boolean isAvailable() {
        return reservations.stream().noneMatch(Reservation::isActive);
    }
}
