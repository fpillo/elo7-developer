package com.elo7.search.controllers;

import com.elo7.search.domains.errors.ResponseError;
import com.elo7.search.exceptions.SaveMovieException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandlerController {

    @ExceptionHandler(SaveMovieException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseError handleSaveMovieException(final SaveMovieException ex) {
        return new ResponseError(ex.getMessage());
    }

}
