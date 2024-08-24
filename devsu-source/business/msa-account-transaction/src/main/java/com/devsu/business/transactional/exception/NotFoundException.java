package com.devsu.business.transactional.exception;

import java.text.MessageFormat;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Exception e) {
        super(e);
    }
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String messageFormat, Object... params) {
        super(MessageFormat.format(messageFormat, params));
    }
}
