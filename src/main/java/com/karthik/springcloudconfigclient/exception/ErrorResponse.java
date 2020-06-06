package com.karthik.springcloudconfigclient.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class ErrorResponse {
    private final int errorCode;
    private final String message;
    private final Date timestamp;
}
