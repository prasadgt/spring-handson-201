package com.shopping.shoppingCartApp.exceptions;

import org.springframework.stereotype.Component;

@Component
public class ExceptionResponse {

    private Integer errorCode;
    private String errorDescription;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
