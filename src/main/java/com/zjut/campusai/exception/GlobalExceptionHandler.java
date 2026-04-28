package com.zjut.campusai.exception;

import com.zjut.campusai.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler({
            StudentNotFoundException.class,
            CourseNotFoundException.class,
            StudentCourseNotFoundException.class
    })
    public ResponseEntity<ApiResponse<?>> handleNotFound(RuntimeException ex) {
        return buildResponse(404, ex.getMessage());
    }

    @ExceptionHandler(InvalidScoreException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(RuntimeException ex) {
        return buildResponse(400, ex.getMessage());
    }

    private ResponseEntity<ApiResponse<?>> buildResponse(int code, String message) {
        ApiResponse<?> error = ApiResponse.error(code,message);
        return ResponseEntity.status(code).body(error);
    }
}