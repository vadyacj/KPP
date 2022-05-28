package com.example.RestService.advices;

import com.example.RestService.logger.MyLogger;
import com.example.RestService.responses.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.apache.logging.log4j.Level;

@ControllerAdvice
public class MainAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response> handleException(@NotNull MethodArgumentTypeMismatchException e) {
        var message = "No argument provided in " + e.getParameter();
        MyLogger.log(Level.ERROR, message);
        return new ResponseEntity<>(new Response(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Response> handleException(@NotNull ArithmeticException e) {
        var message = "Error in calculation: " + e.getMessage();
        MyLogger.log(Level.ERROR, message);
        return new ResponseEntity<>(new Response(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Response> handleException(@NotNull NumberFormatException e) {
        var message = "Can't convert value to int";
        MyLogger.log(Level.ERROR, message);
        return new ResponseEntity<>(new Response(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleException(@NotNull IllegalArgumentException e) {
        var message = "Null argument " + e.getMessage();
        MyLogger.log(Level.ERROR, message);
        return new ResponseEntity<>(new Response(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(@NotNull Exception e) {
        var message = "Error code 500";
        MyLogger.log(Level.ERROR, message);
        return new ResponseEntity<>(new Response(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
