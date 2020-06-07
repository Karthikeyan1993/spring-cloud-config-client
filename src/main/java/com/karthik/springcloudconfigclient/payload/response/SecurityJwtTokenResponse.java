package com.karthik.springcloudconfigclient.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SecurityJwtTokenResponse {
    private String tokenType;
    private String token;
}
