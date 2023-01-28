package com.example.riegoback.Exceptions;

public class ExceptionController extends Exception{
    public ExceptionController() {
        super();
    }

    public ExceptionController(String message) {
        super(message);
    }

    public ExceptionController(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionController(Throwable cause) {
        super(cause);
    }

    protected ExceptionController(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
