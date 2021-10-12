package com.eleks.academy.pharmagator.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleException(ResponseStatusException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getReason());
        return new ResponseEntity<>(apiError, ex.getStatus());
    }
}
