package com.yorra.twinkle.exception;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<ExceptionResponseMessage> create(HttpStatus httpStatus, Exception e, HttpServletRequest request) {
        ExceptionResponseMessage exceptionResponseMessage = new ExceptionResponseMessage(httpStatus.value(), e.getMessage(), request.getRequestURI(), httpStatus.getReasonPhrase());
        return ResponseEntity.status(httpStatus).body(exceptionResponseMessage);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseMessage> handlerMaximumFileException(FileSizeLimitExceededException e, HttpServletRequest request) {
        return create(HttpStatus.BAD_REQUEST, e, request);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponseMessage> handlerException(Exception e, HttpServletRequest request) {
//        return create(HttpStatus.INTERNAL_SERVER_ERROR, e, request);
//    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponseMessage> handlerNotFoundException(NotFoundException e, HttpServletRequest request) {
        return create(HttpStatus.NOT_FOUND, e, request);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponseMessage> handlerBadRequestException(BadRequestException e, HttpServletRequest request) {
        return create(HttpStatus.BAD_REQUEST, e, request);
    }

    // statusCode
    // reason
    // path
    // context
    // handle wildcard
}
