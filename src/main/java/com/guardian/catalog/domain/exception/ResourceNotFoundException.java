package com.guardian.catalog.domain.exception;

public class ResourceNotFoundException  extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
