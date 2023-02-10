package com.yorra.twinkle.exception;

public class NotFoundFileException extends RuntimeException {
    public NotFoundFileException() {
    }

    public NotFoundFileException(String message) {
        super(message);
    }
}
