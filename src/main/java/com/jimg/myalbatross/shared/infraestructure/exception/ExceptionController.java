package com.jimg.myalbatross.shared.infraestructure.exception;

import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MyalbatrossException.class)
    public ResponseEntity<CustomError> handle(MyalbatrossException exception) {
        return ResponseEntity
                .status(exception.getStatus())
                .body(new CustomError(exception.getMessage()));
    }
}
