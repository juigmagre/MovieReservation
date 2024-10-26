package com.jimg.myalbatross.reservation.application.service;

import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.movie.domain.repository.MovieRepository;
import com.jimg.myalbatross.modules.reservation.application.dto.ReservationCreateRequest;
import com.jimg.myalbatross.modules.reservation.application.service.ReservationCreator;
import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import com.jimg.myalbatross.modules.reservation.domain.repository.ReservationRepository;
import com.jimg.myalbatross.modules.user.application.dto.UserResponse;
import com.jimg.myalbatross.modules.user.application.mapper.UserMapper;
import com.jimg.myalbatross.modules.user.application.service.UserFinder;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.movie.domain.MovieMother;
import com.jimg.myalbatross.reservation.application.dto.ReservationCreateRequestMother;
import com.jimg.myalbatross.shared.application.UnitTestCase;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import com.jimg.myalbatross.user.application.dto.UserResponseMother;
import com.jimg.myalbatross.user.domain.entity.UserMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationCreatorTest extends UnitTestCase {
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserFinder userFinder;

    private ReservationCreator reservationCreator;

    @BeforeEach
    void setUp() {
        reservationCreator = new ReservationCreator(reservationRepository, movieRepository, userMapper, userFinder);
    }

    @Test
    void shouldCreateReservation() {
        ReservationCreateRequest request = ReservationCreateRequestMother.random();
        Movie movie = MovieMother.randomWithId(request.getMovieId());
        User user = UserMother.randomWithId(request.getUserId());
        UserResponse userResponse = UserResponseMother.withUser(user);

        when(reservationRepository.existsById(request.getReservationId())).thenReturn(false);
        when(movieRepository.findById(request.getMovieId())).thenReturn(Optional.of(movie));
        when(userFinder.findById(request.getUserId())).thenReturn(userResponse);
        when(userMapper.toUser(userResponse)).thenReturn(user);

        assertDoesNotThrow(() -> reservationCreator.reserve(request));

        ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);
        verify(movieRepository, times(1)).findById(request.getMovieId());
        verify(reservationRepository, times(1)).save(reservationCaptor.capture());

        Reservation savedReservation = reservationCaptor.getValue();
        assertEquals(request.getReservationId(), savedReservation.getId());
        assertEquals(user, savedReservation.getUser());
        assertEquals(movie, savedReservation.getMovie());
        assertEquals(request.getEndDate(), savedReservation.getEndDate());
    }

    @Test
    void shouldThrowExceptionWhenReservationExists() {
        ReservationCreateRequest request = ReservationCreateRequestMother.random();

        when(reservationRepository.existsById(request.getReservationId())).thenReturn(true);

        MyalbatrossException exception = assertThrows(MyalbatrossException.class, () -> reservationCreator.reserve(request));
        verify(reservationRepository, times(1)).existsById(request.getReservationId());
        verify(movieRepository, never()).findById(any());
        verify(userFinder, never()).findById(any());
        verify(movieRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.RESERVATION_ALREADY_EXISTS, exception);
    }

    @Test
    void shouldThrowMyalbatrossExceptionWhenReservationExceedsDays() {
        ReservationCreateRequest request = ReservationCreateRequestMother.randomWithExceededDays();
        Movie movie = MovieMother.randomWithId(request.getMovieId());
        User user = UserMother.randomWithId(request.getUserId());
        UserResponse userResponse = UserResponseMother.withUser(user);

        when(reservationRepository.existsById(request.getReservationId())).thenReturn(false);
        when(movieRepository.findById(request.getMovieId())).thenReturn(Optional.of(movie));
        when(userFinder.findById(request.getUserId())).thenReturn(userResponse);
        when(userMapper.toUser(userResponse)).thenReturn(user);

        MyalbatrossException exception = assertThrows(MyalbatrossException.class, () -> reservationCreator.reserve(request));

        verify(movieRepository, times(1)).findById(request.getMovieId());
        verify(userFinder, times(1)).findById(request.getUserId());
        verify(reservationRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.RESERVATION_TOO_MANY_DAYS, exception);
    }

    @Test
    void shouldThrowMyalbatrossExceptionWhenMovieNotAvailable() {
        ReservationCreateRequest request = ReservationCreateRequestMother.randomWithExceededDays();
        Movie movie = MovieMother.randomNotAvailable();

        when(reservationRepository.existsById(request.getReservationId())).thenReturn(false);
        when(movieRepository.findById(request.getMovieId())).thenReturn(Optional.of(movie));

        MyalbatrossException exception = assertThrows(MyalbatrossException.class, () -> reservationCreator.reserve(request));
        verify(movieRepository, times(1)).findById(request.getMovieId());
        verify(userFinder, never()).findById(any());
        verify(reservationRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.MOVIE_NOT_AVAILABLE, exception);
    }
}
