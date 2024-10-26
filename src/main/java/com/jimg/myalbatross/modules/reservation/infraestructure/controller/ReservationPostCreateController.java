package com.jimg.myalbatross.modules.reservation.infraestructure.controller;

import com.jimg.myalbatross.modules.reservation.application.dto.ReservationCreateRequest;
import com.jimg.myalbatross.modules.reservation.application.service.ReservationCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservation")
public class ReservationPostCreateController {
    private final ReservationCreator reservationCreator;

    @PostMapping
    public void Reserve(@RequestBody ReservationCreateRequest request) {
        reservationCreator.reserve(request);
    }
}
