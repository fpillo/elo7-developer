package com.elo7.search.exceptions;

public class SaveMovieException extends RuntimeException {

    private static final String MESSAGE = "Error while saving %s.";

    public SaveMovieException(final String id, final Throwable cause) {
        super(String.format(MESSAGE, id), cause);
    }

}
