package com.karthik.springcloudconfigclient.exception;

public class UsernameExistException extends RuntimeException {
    public UsernameExistException() {
        super("Supplied Username Already Exist");
    }
}
