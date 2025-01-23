package com.Assignment.TODO.exception;

import com.Assignment.TODO.Dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = OurException.class)
    public ResponseEntity<Response> handleOurException(OurException e){
        Response response = new Response(null, e.getMessage(), null, e.getStatusCode(), null, null,null,null);
        return ResponseEntity.status(e.getStatusCode()).body(response);
    }
}
