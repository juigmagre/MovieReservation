package com.jimg.myalbatross.modules.user.domain.entity;

import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import com.jimg.myalbatross.modules.user.domain.vo.UserBirth;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    @Column(unique = true)
    private String mail;
    private String username;
    @Embedded
    private UserBirth birthDate;
    @OneToMany(mappedBy = "user")
    private Set<Reservation> reservations;

    public User(UUID id, String mail, String username, UserBirth birthDate) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.birthDate = birthDate;
    }

    public LocalDateTime getBirthDate() {
        return this.birthDate.value();
    }

    public boolean hasReservations() {
        return nonNull(reservations) && !reservations.isEmpty();
    }
}
