package com.eleks.academy.pharmagator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidIdentifierException extends RuntimeException {

    public InvalidIdentifierException(Long id) {
        super("Invalid id: " + id);
    }

    public InvalidIdentifierException() {
        super("Invalid id.");
    }
}
