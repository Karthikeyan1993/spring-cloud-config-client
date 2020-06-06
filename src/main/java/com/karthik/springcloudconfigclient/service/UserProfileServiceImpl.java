package com.karthik.springcloudconfigclient.service;

import com.karthik.springcloudconfigclient.model.UserProfile;
import com.karthik.springcloudconfigclient.payload.SignUpRequest;
import com.karthik.springcloudconfigclient.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final PasswordEncoder passwordEncoder;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder) {
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserProfile save(SignUpRequest request) {
        UserProfile profile = new UserProfile();
        profile.setUsername(request.getUsername());
        profile.setEmail(request.getEmail());
        profile.setPassword(this.passwordEncoder.encode(request.getPassword()));
        return this.userProfileRepository.save(profile);
    }
}
