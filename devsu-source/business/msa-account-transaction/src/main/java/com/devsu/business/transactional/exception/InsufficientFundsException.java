package com.devsu.business.transactional.exception;

import java.text.MessageFormat;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(Exception e) {
        super(e);
    }
    public InsufficientFundsException(String message) {
        super(message);
    }
    public InsufficientFundsException(String messageFormat, Object... params) {
        super(MessageFormat.format(messageFormat, params));
    }
}
