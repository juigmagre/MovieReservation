package com.jimg.myalbatross.modules.reservation.domain.entity;

import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Reservation {
    @Id
    private UUID id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Movie movie;
    @CreationTimestamp
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean cancelled;

    public Reservation(UUID id, User user, Movie movie, LocalDateTime endDate) {
        validateEndDate(endDate);
        this.id = id;
        this.user = user;
        this.movie = movie;
        this.endDate = endDate;
        this.cancelled = false;
    }

    private void validateEndDate(LocalDateTime endDate) {
        long daysBetween = java.time.Duration.between(LocalDateTime.now(), endDate).toDays();

        if (daysBetween > 7) {
            throw new MyalbatrossException(MyalbatrossError.RESERVATION_TOO_MANY_DAYS);
        }
    }

    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return !this.cancelled && now.isAfter(this.getStartDate()) && now.isBefore(this.getEndDate());
    }
}
