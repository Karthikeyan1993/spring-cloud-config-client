package com.karthik.springcloudconfigclient.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SecurityJwtTokenProvider {

    @Value("${app.security.jwtSecret}")
    private String jwtSecret;

    @Value("${app.security.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    @Value("${app.security.jwtTokenPrefix}")
    private String jwtTokenPrefix;

    @Value("${app.security.jwtHeaderString}")
    private String jwtHeaderString;


    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

}
