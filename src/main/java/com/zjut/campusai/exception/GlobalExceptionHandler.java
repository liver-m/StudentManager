package com.zjut.campusai.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerStudentNotFound(StudentNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(404,ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(404).body(errorResponse);
    }
}
