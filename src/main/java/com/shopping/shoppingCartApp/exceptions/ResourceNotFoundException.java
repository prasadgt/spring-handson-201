package com.shopping.shoppingCartApp.exceptions;

import org.springframework.stereotype.Component;

@Component
public class ResourceNotFoundException extends Exception{

    private String message;

    public ResourceNotFoundException(){}

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
