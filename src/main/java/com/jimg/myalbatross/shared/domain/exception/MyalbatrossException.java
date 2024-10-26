package com.jimg.myalbatross.shared.domain.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class MyalbatrossException extends RuntimeException {
    private final MyalbatrossError error;

    @Override
    public String getMessage() {
        return error.getMessage();
    }

    public HttpStatus getStatus() {
        return error.getStatus();
    }
}
