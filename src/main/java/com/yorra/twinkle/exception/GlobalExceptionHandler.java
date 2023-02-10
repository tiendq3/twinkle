package com.yorra.twinkle.exception;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseEntity<?> handlerMaximumFileException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("maximum file size: 1MB ");
    }

    @ExceptionHandler(NotFoundFileException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlerNotFoundFileException() {
    }

    // statusCode
    // reason
    // path
    // context
    // handle wildcard
}
