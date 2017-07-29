package com.elo7.search.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
