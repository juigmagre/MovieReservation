package com.jimg.myalbatross.modules.reservation.infraestructure.controller;

import com.jimg.myalbatross.modules.reservation.application.service.ReservationModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservation")
public class ReservationPutCancelController {
    private final ReservationModifier reservationModifier;

    @PutMapping("cancel")
    public void cancel(@RequestParam UUID id) {
        reservationModifier.cancel(id);
    }
}
