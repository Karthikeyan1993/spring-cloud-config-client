package com.karthik.springcloudconfigclient.service;

import com.karthik.springcloudconfigclient.payload.request.LoginRequest;
import com.karthik.springcloudconfigclient.payload.request.UserProfileRequest;
import com.karthik.springcloudconfigclient.payload.response.SecurityJwtTokenResponse;

public interface UserProfileService {
    void signUp(UserProfileRequest request);

    SecurityJwtTokenResponse signIn(LoginRequest request);

}
