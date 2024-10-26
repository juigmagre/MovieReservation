package com.jimg.myalbatross.modules.reservation.application.service;

import com.jimg.myalbatross.modules.reservation.application.dto.ReservationResponse;
import com.jimg.myalbatross.modules.reservation.application.mapper.ReservationMapper;
import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import com.jimg.myalbatross.modules.reservation.domain.repository.ReservationRepository;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationFinder {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationResponse findById(UUID id) {
        log.info("Finding reservation with id: {}", id);
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new MyalbatrossException(MyalbatrossError.RESERVATION_NOT_EXISTS));

        return reservationMapper.toReservationResponse(reservation);
    }

    public List<ReservationResponse> findAllOrderedBuMovieName(Sort.Direction sort) {
        log.info("Finding all ordered by movie names");
        Sort direction = nonNull(sort) ? Sort.by(sort, "movie.title") : Sort.unsorted();
        List<Reservation> reservations = reservationRepository.findAll(direction);

        return reservationMapper.toReservationResponseList(reservations);
    }
}
