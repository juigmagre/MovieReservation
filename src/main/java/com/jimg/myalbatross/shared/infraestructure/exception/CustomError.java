package com.jimg.myalbatross.shared.infraestructure.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class CustomError {
    final String message;
    LocalDateTime dateTime = LocalDateTime.now();
}
