package com.yorra.twinkle.exceptionhandler;

public class NotFoundFileException extends RuntimeException {
    public NotFoundFileException() {
    }

    public NotFoundFileException(String message) {
        super(message);
    }
}
