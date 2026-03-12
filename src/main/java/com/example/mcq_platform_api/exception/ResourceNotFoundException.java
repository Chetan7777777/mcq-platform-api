package com.example.mcq_platform_api.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String messagge){
        super(messagge);
    }
}
