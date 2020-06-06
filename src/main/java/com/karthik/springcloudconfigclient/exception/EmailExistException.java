package com.karthik.springcloudconfigclient.exception;

public class EmailExistException extends RuntimeException {
    public EmailExistException(){
        super("Supplied Email Already Exist");
    }
}
