package com.karthik.springcloudconfigclient.controller;

import com.karthik.springcloudconfigclient.model.UserProfile;
import com.karthik.springcloudconfigclient.payload.SignUpRequest;
import com.karthik.springcloudconfigclient.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("sign-up")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
    }
}
