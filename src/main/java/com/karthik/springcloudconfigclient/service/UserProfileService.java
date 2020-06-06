package com.karthik.springcloudconfigclient.service;

import com.karthik.springcloudconfigclient.model.UserProfile;
import com.karthik.springcloudconfigclient.payload.SignUpRequest;

public interface UserProfileService {
    UserProfile save(SignUpRequest request);
}
