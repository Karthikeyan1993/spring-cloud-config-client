package com.karthik.springcloudconfigclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler({UsernameExistException.class})
    public ResponseEntity<Object> handleUsernameExistException(UsernameExistException e) {
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({EmailExistException.class})
    public ResponseEntity<Object> handleEmailExistException(EmailExistException e) {
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
