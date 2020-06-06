package com.karthik.springcloudconfigclient.service;

import com.karthik.springcloudconfigclient.model.UserProfile;
import com.karthik.springcloudconfigclient.payload.SignUpRequest;
import com.karthik.springcloudconfigclient.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserProfileServiceImpl implements UserProfileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileServiceImpl.class);
    private final UserProfileRepository userProfileRepository;

    private final PasswordEncoder passwordEncoder;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserProfile signUp(SignUpRequest request) {
        LOGGER.info("New UserProfile Request Received At : {}", new Date());
        UserProfile profile = new UserProfile();
        profile.setUsername(request.getUsername());
        profile.setEmail(request.getEmail());
        profile.setPassword(this.passwordEncoder.encode(request.getPassword()));
        return this.userProfileRepository.save(profile);
    }
}
