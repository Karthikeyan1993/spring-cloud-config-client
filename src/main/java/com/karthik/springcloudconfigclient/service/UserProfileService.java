package com.karthik.springcloudconfigclient.service;

import com.karthik.springcloudconfigclient.model.UserProfile;
import com.karthik.springcloudconfigclient.payload.SignUpRequest;

public interface UserProfileService {
    void signUp(SignUpRequest request);
    UserProfile findByUsernameOrEmail(String username,String email);
}
