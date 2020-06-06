package com.karthik.springcloudconfigclient.payload;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SignUpRequest implements Serializable {
    private String username;
    private String email;
    private String password;
}
