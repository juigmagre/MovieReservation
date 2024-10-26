package com.jimg.myalbatross.reservation.application.service;

import com.jimg.myalbatross.modules.reservation.application.service.ReservationModifier;
import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import com.jimg.myalbatross.modules.reservation.domain.repository.ReservationRepository;
import com.jimg.myalbatross.reservation.domain.ReservationMother;
import com.jimg.myalbatross.shared.application.UnitTestCase;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class ReservationModifierTest extends UnitTestCase {
    @Mock
    private ReservationRepository reservationRepository;

    private ReservationModifier reservationModifier;

    @BeforeEach
    public void setUp() {
        reservationModifier = new ReservationModifier(reservationRepository);
    }

    @Test
    public void shouldCancelReservation() {
        Reservation reservation = ReservationMother.randomActive();
        ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);

        when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.of(reservation));

        assertDoesNotThrow(() -> reservationModifier.cancel(reservation.getId()));

        verify(reservationRepository, times(1)).findById(reservation.getId());
        verify(reservationRepository, times(1)).save(reservationCaptor.capture());
        Reservation modifiedReservation = reservationCaptor.getValue();
        Assertions.assertTrue(modifiedReservation.isCancelled());
    }

    @Test
    public void shouldThrowMyalbatrossException() {
        Reservation reservation = ReservationMother.randomActive();

        when(reservationRepository.findById(reservation.getId())).thenReturn(Optional.empty());

        MyalbatrossException expectedException = Assertions.assertThrows(MyalbatrossException.class, () -> reservationModifier.cancel(reservation.getId()));

        verify(reservationRepository, times(1)).findById(reservation.getId());
        verify(reservationRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.RESERVATION_NOT_EXISTS, expectedException);
    }
}
