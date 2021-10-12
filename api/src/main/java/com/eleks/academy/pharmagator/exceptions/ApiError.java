package com.eleks.academy.pharmagator.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ApiError {

    private String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ApiError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
