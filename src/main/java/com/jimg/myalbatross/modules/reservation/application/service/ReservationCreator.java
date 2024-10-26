package com.jimg.myalbatross.modules.reservation.application.service;

import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.movie.domain.repository.MovieRepository;
import com.jimg.myalbatross.modules.reservation.application.dto.ReservationCreateRequest;
import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import com.jimg.myalbatross.modules.reservation.domain.repository.ReservationRepository;
import com.jimg.myalbatross.modules.user.application.mapper.UserMapper;
import com.jimg.myalbatross.modules.user.application.service.UserFinder;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationCreator {
    private final ReservationRepository reservationRepository;
    private final MovieRepository movieRepository;
    private final UserMapper userMapper;
    private final UserFinder userFinder;

    public void reserve(ReservationCreateRequest request) {
        log.info("Creating reservation with id: {}, userId: {}, movieId: {} and endDate: {}", request.getReservationId(), request.getUserId(), request.getMovieId(), request.getEndDate());
        if (reservationRepository.existsById(request.getReservationId())) {
            throw new MyalbatrossException(MyalbatrossError.RESERVATION_ALREADY_EXISTS);
        }
        Movie movie = movieRepository.findById(request.getMovieId()).orElseThrow(() -> new MyalbatrossException(MyalbatrossError.MOVIE_NOT_EXISTS));
        if (!movie.isAvailable()) {
            throw new MyalbatrossException(MyalbatrossError.MOVIE_NOT_AVAILABLE);
        }
        User user = userMapper.toUser(userFinder.findById(request.getUserId()));

        Reservation reservation = new Reservation(request.getReservationId(), user, movie, request.getEndDate());
        reservationRepository.save(reservation);
    }
}
