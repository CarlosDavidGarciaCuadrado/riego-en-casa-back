package com.example.riegoback.Exceptions;

public class ExceptionConexion extends Exception{
    public ExceptionConexion() {
        super();
    }

    public ExceptionConexion(String message) {
        super(message);
    }

    public ExceptionConexion(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionConexion(Throwable cause) {
        super(cause);
    }

    protected ExceptionConexion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
