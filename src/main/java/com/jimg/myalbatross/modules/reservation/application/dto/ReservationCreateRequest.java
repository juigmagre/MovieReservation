package com.jimg.myalbatross.modules.reservation.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ReservationCreateRequest {
    private UUID reservationId;
    private UUID userId;
    private UUID movieId;
    private LocalDateTime endDate;
}
