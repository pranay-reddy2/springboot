package com.example.ecommerce.exception;

import com.example.ecommerce.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handle(RuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse(ex.getMessage()));
    }
}
