package com.javainuse.bootmysqlcrud.exception;

public class NumberException extends Exception {
    private static final long serialVersionUID = 1L;

    public NumberException() {
    }

    public NumberException(String message) {
        super(message);
    }
}
