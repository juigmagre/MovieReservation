package com.jimg.myalbatross.modules.reservation.infraestructure.controller;

import com.jimg.myalbatross.modules.reservation.application.dto.ReservationResponse;
import com.jimg.myalbatross.modules.reservation.application.service.ReservationFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservation")
public class ReservationGetFindController {
    private final ReservationFinder reservationFinder;

    @GetMapping
    public ReservationResponse findById(@RequestParam UUID id) {
        return reservationFinder.findById(id);
    }

    @GetMapping("/all")
    public List<ReservationResponse> findAllOrderedByMovieTitle(@RequestParam(required = false) Sort.Direction sort) {
        return reservationFinder.findAllOrderedBuMovieName(sort);
    }
}
