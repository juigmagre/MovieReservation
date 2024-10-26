package com.jimg.myalbatross.modules.reservation.application.service;

import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import com.jimg.myalbatross.modules.reservation.domain.repository.ReservationRepository;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationModifier {
    private final ReservationRepository reservationRepository;

    public void cancel(UUID reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new MyalbatrossException(MyalbatrossError.RESERVATION_NOT_EXISTS));
        reservation.setCancelled(true);
        reservationRepository.save(reservation);
    }
}
