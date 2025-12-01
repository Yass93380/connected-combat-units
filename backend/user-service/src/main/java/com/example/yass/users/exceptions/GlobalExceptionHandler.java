package com.example.yass.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public record Error(int code, String message){}

    @ExceptionHandler(exception = NoHandlerFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Error resourceNotFound(NoHandlerFoundException ex) {
        return new Error(404, "L'URL demandée n'éxiste pas.");
    }

}
