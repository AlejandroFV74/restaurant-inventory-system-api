package com.aafv.restaurantapi.exceptions;

public class BusinessRuleException extends  RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
