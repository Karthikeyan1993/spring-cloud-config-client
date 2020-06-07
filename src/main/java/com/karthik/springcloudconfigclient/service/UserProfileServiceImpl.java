package com.karthik.springcloudconfigclient.service;

import com.karthik.springcloudconfigclient.exception.EmailExistException;
import com.karthik.springcloudconfigclient.exception.UsernameExistException;
import com.karthik.springcloudconfigclient.model.UserProfile;
import com.karthik.springcloudconfigclient.payload.request.LoginRequest;
import com.karthik.springcloudconfigclient.payload.response.SecurityJwtTokenResponse;
import com.karthik.springcloudconfigclient.payload.request.UserProfileRequest;
import com.karthik.springcloudconfigclient.repository.UserProfileRepository;
import com.karthik.springcloudconfigclient.security.SecurityJwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class UserProfileServiceImpl implements UserProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    private final UserProfileRepository userProfileRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final SecurityJwtTokenProvider securityJwtTokenProvider;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, SecurityJwtTokenProvider securityJwtTokenProvider) {
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.securityJwtTokenProvider = securityJwtTokenProvider;
    }

    @Override
    public void signUp(UserProfileRequest request) {

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
        LOGGER.info("UserProfile Created for {}", request.getUsername());
    }

    @Override
    public SecurityJwtTokenResponse signIn(LoginRequest request) {

        LOGGER.info("Login Request Received At : {}", new Date());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        String token = this.securityJwtTokenProvider.generateToken(request.getUsername());
        SecurityJwtTokenResponse response = SecurityJwtTokenResponse.builder()
                .tokenType("Bearer")
                .token(token)
                .build();
        LOGGER.info("User Authenticated Successfully");
        return response;
    }

    @Override
    public Optional<UserProfile> findByUsernameOrEmail(String username, String email) {
        return this.userProfileRepository
                .findUserProfileByUsernameOrEmail(username, email);
    }
}
