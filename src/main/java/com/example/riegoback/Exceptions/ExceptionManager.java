package com.example.riegoback.Exceptions;

public class ExceptionManager extends Exception{
    public ExceptionManager() {
        super();
    }

    public ExceptionManager(String message) {
        super(message);
    }

    public ExceptionManager(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionManager(Throwable cause) {
        super(cause);
    }

    protected ExceptionManager(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
