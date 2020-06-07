package com.karthik.springcloudconfigclient.service;

import com.karthik.springcloudconfigclient.model.UserProfile;
import com.karthik.springcloudconfigclient.payload.request.LoginRequest;
import com.karthik.springcloudconfigclient.payload.response.SecurityJwtTokenResponse;
import com.karthik.springcloudconfigclient.payload.request.UserProfileRequest;

import java.util.Optional;

public interface UserProfileService {
    void signUp(UserProfileRequest request);

    SecurityJwtTokenResponse signIn(LoginRequest request);

    Optional<UserProfile> findByUsernameOrEmail(String username, String email);
}
