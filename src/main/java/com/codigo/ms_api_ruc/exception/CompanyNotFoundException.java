package com.codigo.ms_api_ruc.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
