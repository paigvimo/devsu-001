package com.devsu.business.customer.exception;

import java.text.MessageFormat;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String messageFormat, Object... params) {
        super(MessageFormat.format(messageFormat, params));
    }
}
