package com.karthik.springcloudconfigclient.controller;

import com.karthik.springcloudconfigclient.payload.request.LoginRequest;
import com.karthik.springcloudconfigclient.payload.response.SecurityJwtTokenResponse;
import com.karthik.springcloudconfigclient.payload.request.UserProfileRequest;
import com.karthik.springcloudconfigclient.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final UserProfileService userProfileService;

    public AuthController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("sign-up")
    public void signUp(@RequestBody UserProfileRequest userProfileRequest) {
        this.userProfileService.signUp(userProfileRequest);
    }

    @PostMapping("sign-in")
    public SecurityJwtTokenResponse signIn(@RequestBody LoginRequest loginRequest) {
        return this.userProfileService.signIn(loginRequest);
    }
}
