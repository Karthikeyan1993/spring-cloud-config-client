package com.karthik.springcloudconfigclient.service;

import com.karthik.springcloudconfigclient.exception.EmailExistException;
import com.karthik.springcloudconfigclient.exception.UsernameExistException;
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
    public void signUp(SignUpRequest request) {
        LOGGER.info("New UserProfile Request Received At : {}", new Date());

        if (userProfileRepository.findUserProfileByUsername(request.getUsername()) != null)
            throw new UsernameExistException();

        if (userProfileRepository.findUserProfileByEmail(request.getEmail()) != null) throw new EmailExistException();

        UserProfile profile = UserProfile.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(false)
                .build();
        this.userProfileRepository.save(profile);
        LOGGER.info("UserProfile Created for {}",request.getUsername());
    }
}
