package com.karthik.springcloudconfigclient.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserProfileRequest implements Serializable {
    private String username;
    private String email;
    private String password;
}
