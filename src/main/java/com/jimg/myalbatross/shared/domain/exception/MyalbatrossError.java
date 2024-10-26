package com.jimg.myalbatross.shared.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MyalbatrossError {
    USER_ALREADY_EXISTS("User already exists", HttpStatus.CONFLICT),
    MOVIE_ALREADY_EXISTS("Movie already exists", HttpStatus.CONFLICT),
    RESERVATION_ALREADY_EXISTS("Reservation already exists", HttpStatus.CONFLICT),
    USER_NOT_EXISTS("User doesn't exist", HttpStatus.NOT_FOUND),
    MOVIE_NOT_EXISTS("Movie doesn't exist", HttpStatus.NOT_FOUND),
    RESERVATION_NOT_EXISTS("Reservation doesn't exist", HttpStatus.NOT_FOUND),
    MOVIE_NOT_AVAILABLE("Movie is not available", HttpStatus.BAD_REQUEST),
    RESERVATION_TOO_MANY_DAYS("Reservation must last less than 7 days", HttpStatus.LENGTH_REQUIRED),
    USER_HAS_RESERVATIONS("User cannot be deleted because has reservations", HttpStatus.CONFLICT),
    USER_AGE_NOT_CORRECT("User must be older than 18 years", HttpStatus.NOT_ACCEPTABLE),
    MOVIE_TITLE_EMPTY("Title is empty", HttpStatus.BAD_REQUEST),
    MAIL_ALREADY_EXISTS("Mail already exists", HttpStatus.CONFLICT),
    ;

    private final String message;
    private final HttpStatus status;
}
