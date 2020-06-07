package com.karthik.springcloudconfigclient.exception;

public class JwtTokenMissingException extends RuntimeException {
    public JwtTokenMissingException(String message) {
        super(message);
    }
}
