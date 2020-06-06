package com.karthik.springcloudconfigclient.controller;

import com.karthik.springcloudconfigclient.payload.SignInRequest;
import com.karthik.springcloudconfigclient.payload.SignUpRequest;
import com.karthik.springcloudconfigclient.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final UserProfileService userProfileService;

    private final AuthenticationManager authenticationManager;

    public AuthController(UserProfileService userProfileService, AuthenticationManager authenticationManager) {
        this.userProfileService = userProfileService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("sign-up")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        this.userProfileService.signUp(signUpRequest);
    }

    @PostMapping("sign-in")
    public void signIn(@RequestBody SignInRequest signInRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword()));
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
    }
}
