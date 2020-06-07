package com.karthik.springcloudconfigclient.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class SecurityAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAuthenticationFilter.class);

    @Value("${app.security.jwtTokenPrefix}")
    private String jwtTokenPrefix;

    @Value("${app.security.jwtHeaderString}")
    private String jwtHeaderString;

    @Autowired
    private SecurityJwtTokenProvider securityJwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final Optional<String> jwt = getJwtFromRequest(httpServletRequest);
        jwt.ifPresent(authToken -> {
            if (securityJwtTokenProvider.validateToken(authToken)) {
                String username = securityJwtTokenProvider.getUserNameFromJWT(authToken);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                LOGGER.info("Authorized Successfully");
            }
        });
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    Optional<String> getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtHeaderString);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtTokenPrefix)) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }
}
