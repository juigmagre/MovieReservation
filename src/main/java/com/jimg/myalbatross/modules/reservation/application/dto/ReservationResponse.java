package com.jimg.myalbatross.modules.reservation.application.dto;

import com.jimg.myalbatross.modules.movie.application.dto.MovieResponse;
import com.jimg.myalbatross.modules.user.application.dto.UserResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ReservationResponse {
    private UUID id;
    private UserResponse user;
    private MovieResponse movie;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean cancelled;
}
